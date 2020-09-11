package com.laurent.quizzio.data.network

import com.laurent.quizzio.data.models.Quizz
import retrofit2.http.GET
import retrofit2.http.Path

interface INetworkAPI {

    @GET("quizz/{id}")
    suspend fun getQuizz(@Path("id") id: Int) : Quizz
}