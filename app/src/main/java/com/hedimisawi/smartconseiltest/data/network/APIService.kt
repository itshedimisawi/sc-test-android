package com.hedimisawi.smartconseiltest.data.network

import com.hedimisawi.smartconseiltest.data.models.dtos.Post
import com.hedimisawi.smartconseiltest.data.models.dtos.User
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    // Endpoint to get users
    @GET("users")
    fun getUsers(): Call<List<User>>
}