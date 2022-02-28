package com.psp.collegeforum.Retrofit

import retrofit2.Response
import retrofit2.http.GET

interface Api{

    @GET("/todos")
    suspend fun getQuestion():Response<List<Question>>

}