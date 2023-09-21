package com.programmer270487.filamentapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programmer270487.filamentapp.data.UserRepository
import com.programmer270487.filamentapp.views.UserViewModel

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}