package com.programmer270487.filamentapp.data

import retrofit2.Response

class UserRepository(private val apiService: ApiService) {
    suspend fun getUsers(): Response<List<User>> {
        return apiService.getUsers()
    }
    suspend fun getUser(): Response<User> {
        return apiService.getUser()
    }
}