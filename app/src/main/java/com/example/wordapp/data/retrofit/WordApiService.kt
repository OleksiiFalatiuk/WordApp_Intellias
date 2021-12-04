package com.example.wordapp.data.retrofit

import com.example.wordapp.data.retrofit.response.MeaningWordResponse
import com.example.wordapp.data.retrofit.response.MeaningsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApiService {

    @GET("entries/en/{word}")
    suspend fun loadMeanings(
        @Path("word") word: String
    ): MeaningsResponse

    @GET("entries/en/{word}/meanings")
    suspend fun loadResponse(
        @Path("word") word: String
    ): MeaningWordResponse

}