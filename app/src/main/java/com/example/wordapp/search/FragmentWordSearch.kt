package com.example.wordapp.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.wordapp.R
import com.example.wordapp.result.FragmentWordResult

class FragmentWordSearch: Fragment() {

    private var listener: WordSearchClickListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is WordSearchClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_word_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edit = view.findViewById<EditText>(R.id.etSearch)
        val btn = view.findViewById<Button>(R.id.btnSearch)
        btn.setOnClickListener{
            listener?.toSendData(edit.text.toString())
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    interface WordSearchClickListener{
        fun toSendData(someWord: String)
    }

    companion object{
       fun create() = FragmentWordSearch()
    }
}