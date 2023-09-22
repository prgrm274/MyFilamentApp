package com.programmer270487.filamentapp.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmer270487.filamentapp.data.User
import com.programmer270487.filamentapp.data.UserRepository
import kotlinx.coroutines.launch
import java.io.IOException

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    private val _user = MutableLiveData<User>()
    val users: LiveData<List<User>> get() = _users
    val user: LiveData<User> get() = _user

    fun fetchUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            if (response.isSuccessful) {
                _users.value = response.body()
            } else {
                // Handle error
            }
        }
    }
    fun fetchUser() {
        viewModelScope.launch {
            try {
                val response = repository.getUser()
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    println("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: IOException) {
                println("IO Exception: ${e.message}")
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
    }
}