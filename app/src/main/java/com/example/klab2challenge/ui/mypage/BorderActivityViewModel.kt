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
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        Log.d("asdfsadfasdf", borders.value.toString())
        Log.d("asdfsadfasdf", checkedItem.value.toString())
        return borders.value!![checkedItem.value!!].isUnlocked
    }

    fun requestChangeBorder(checkedBorder: Int) {
        viewModelScope.launch {
            val userInfo = users.value!!.get(0)
            userRepository.requestChangeBorder(userInfo.name, checkedBorder)
            delay(100)
            userRepository.requestUser(userInfo.name)
            delay(100)
            rankingRepository.requestRanking(userInfo.name)
        }
    }


    fun requestBuyBorder(checkedBorder: Int, context: Context) {
        viewModelScope.launch {
            val userInfo = users.value!!.get(0)
            val borderInfo = borders.value!!.get(checkedBorder)
            userRepository.requestBuyBorder(userInfo.name, checkedBorder)
            delay(100)
            borderRepository.requestBorder(userInfo.name, context)
            delay(100)
            userRepository.requestUser(userInfo.name)
        }
    }
}