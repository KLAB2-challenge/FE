package com.example.klab2challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.entity.UserEntity
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    val users = userRepository.users.asLiveData()

    fun insert(user : UserEntity) {
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

    fun requestUser(userName: String) {
        viewModelScope.launch {
            userRepository.requestUser(userName)
        }
    }
}