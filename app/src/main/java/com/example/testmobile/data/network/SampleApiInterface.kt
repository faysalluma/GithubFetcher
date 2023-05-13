package com.example.testmobile.data.network

import com.example.testmobile.data.network.bodies.results.Repository
import retrofit2.Call
import retrofit2.http.*

interface SampleApiInterface {
    @GET("/repositories")
    fun getAllRepositories(): Call<List<Repository>>

    @GET("/repos/{fullname}/languages")
    fun getRepositoryLanguages(@Path("fullname") fullname: String): Call<String>
}