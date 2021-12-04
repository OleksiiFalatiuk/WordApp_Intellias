package com.example.wordapp.data.retrofit

import com.example.wordapp.data.RemoteDataSource
import com.example.wordapp.model.MainWordInformation
import com.example.wordapp.model.Meaning

class RetrofitDataSource(private val api: WordApiService): RemoteDataSource {


    override suspend fun loadMeanings(word: String): MainWordInformation {
        val info = api.loadMeanings(word)
        return MainWordInformation(
            word = info.word,
            phonetic = info.phonetic,
            meanings = api.loadResponse(word).definitions.map { definition ->
                Meaning(
                    meaning = definition.definition
                )
            }

//            info.meanings.map { it ->
//                it.definitions.map { at ->
//                    Meaning(
//                        meaning = at.definition
//                    )
//                }
//            }
        )
    }
}