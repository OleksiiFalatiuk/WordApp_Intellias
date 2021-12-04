package com.example.wordapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wordapp.data.retrofit.RetrofitDataSource
import com.example.wordapp.provider.NetworkModule
import com.example.wordapp.provider.WordRepositoryProvider
import com.example.wordapp.repository.WordRepository
import com.example.wordapp.repository.WordRepositoryImpl
import com.example.wordapp.result.FragmentWordResult
import com.example.wordapp.search.FragmentWordSearch
import kotlinx.serialization.ExperimentalSerializationApi

class MainActivity : AppCompatActivity(),FragmentWordSearch.WordSearchClickListener,WordRepositoryProvider {

    private val networkModule = NetworkModule()
    @ExperimentalSerializationApi
    private val remoteDataSource = RetrofitDataSource(networkModule.api)
    @ExperimentalSerializationApi
    private val wordRepository = WordRepositoryImpl(remoteDataSource)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            toFirstSearchFragment()
        }
    }

    override fun toSendData(someWord: String) {
        toResultFragment(someWord)
    }

    private fun toFirstSearchFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.flMain,
            FragmentWordSearch.create(),
                FragmentWordSearch::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentWordSearch::class.java.simpleName}")
            .commit()
    }

    private fun toResultFragment(someWord: String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMain,
                FragmentWordResult.create(someWord),
                FragmentWordResult::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentWordResult::class.java.simpleName}")
            .commit()
    }

    @ExperimentalSerializationApi
    override fun provideWordRepository(): WordRepository = wordRepository

}
