package com.programmer270487.filamentapp.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    @GET("users")
    suspend fun getUser(): Response<User>
}