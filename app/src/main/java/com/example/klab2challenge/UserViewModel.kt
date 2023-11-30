package com.example.klab2challenge

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.BorderEntity
import com.example.klab2challenge.db.BorderRepository
import com.example.klab2challenge.db.UserEntity
import com.example.klab2challenge.db.UserRepository
import com.example.klab2challenge.ui.mypage.BorderOption
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