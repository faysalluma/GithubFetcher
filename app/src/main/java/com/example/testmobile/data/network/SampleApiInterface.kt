package com.example.testmobile.data.network

import com.example.testmobile.data.network.bodies.results.Branch
import com.example.testmobile.data.network.bodies.results.Contributor
import com.example.testmobile.data.network.bodies.results.Repository
import retrofit2.Call
import retrofit2.http.*

interface SampleApiInterface {
    @GET("/repositories")
    fun getAllRepositories(): Call<List<Repository>>

    @GET("/repos/{reponame}/{username}/branches")
    fun getRepositoryBranches(@Path("reponame") reponame: String, @Path("username") username: String): Call<List<Branch>>

    @GET("/repos/{reponame}/{username}/contributors")
    fun getRepositoryContributors(@Path("reponame") reponame: String, @Path("username") username: String): Call<List<Contributor>>
}