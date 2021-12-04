package com.example.wordapp.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.R
import com.example.wordapp.model.MainWordInformation
import com.example.wordapp.model.Meaning
import com.example.wordapp.provider.WordRepositoryProvider
import com.example.wordapp.search.FragmentWordSearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class FragmentWordResult: Fragment() {

    private val viewResultModel: WordResultViewModel by viewModels{
        WordResultViewModelFactory((requireActivity() as WordRepositoryProvider).provideWordRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_word_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val someWord = arguments?.getSerializable(PARAM_WORD_TO_SEARCH) as? String ?: return
        val text = view.findViewById<TextView>(R.id.tvResult)
        text.text = someWord

        view.findViewById<RecyclerView>(R.id.rvResult).apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            this.adapter = WordResultAdapter()
        }

        viewResultModel.loadMeanings(someWord)

        viewResultModel.wordMean.observe(viewLifecycleOwner,{ state ->
            if(state != null){
                binding(view,state)
            }else{
                errorWasFound()
            }
        })


    }

    private fun errorWasFound(){
        Toast.makeText(requireContext(),"Sorry! Something went wrong - we can not find this word in the system.", Toast.LENGTH_LONG)
            .show()
    }

    private fun binding(view: View,res: MainWordInformation){
        updateWordResult(res)
        val adapter = view.findViewById<RecyclerView>(R.id.rvResult).adapter as WordResultAdapter
        adapter.submitList(res.meanings)
        }




    private fun updateWordResult(res: MainWordInformation){
        view?.findViewById<TextView>(R.id.tvResult)?.text = res.word
        view?.findViewById<TextView>(R.id.tvPhonetic)?.text = res.phonetic

    }


    companion object{
        private const val PARAM_WORD_TO_SEARCH = "word"
        fun create(someWord: String) = FragmentWordResult().also {
            val args = bundleOf(
                PARAM_WORD_TO_SEARCH to someWord
            )
            it.arguments = args
        }
    }
}