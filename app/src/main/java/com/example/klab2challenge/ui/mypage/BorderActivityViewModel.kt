package com.example.klab2challenge.ui.mypage

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.definition._createDefinition

class BorderActivityViewModel(
    private val userRepository: UserRepository,
    private val borderRepository: BorderRepository,
    private val rankingRepository: RankingRepository
) : ViewModel() {
    val borders = borderRepository.borders.asLiveData()
    val users = userRepository.users.asLiveData()
    val rankings = rankingRepository.rankings.asLiveData()
    val _checkedItem = MutableLiveData<Int>()
    val checkedItem: LiveData<Int> get() = _checkedItem
    fun checkItem(item: Int): Boolean {
        _checkedItem.value = item
        return borders.value!![checkedItem.value!!].isUnlocked
    }

    fun requestChangeBorder(checkedBorder: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                changeBorder(checkedBorder)
            }
        }
    }

    suspend fun changeBorder(checkedBorder: Int) {
        val userInfo = users.value!!.get(0)
        userRepository.requestChangeBorder(userInfo.name, checkedBorder)
        userRepository.requestUser(userInfo.name)
        rankingRepository.requestRanking(userInfo.name)
    }

    fun requestBuyBorder(checkedBorder: Int, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                buyBorder(checkedBorder, context)
            }
        }
    }

    suspend fun buyBorder(checkedBorder: Int, context: Context) {
        val userInfo = users.value!!.get(0)
        val borderInfo = borders.value!!.get(checkedBorder)
        userRepository.requestBuyBorder(userInfo.name, checkedBorder, borderInfo.price)
        borderRepository.requestBorder(userInfo.name, context)
        userRepository.requestUser(userInfo.name)
    }
}