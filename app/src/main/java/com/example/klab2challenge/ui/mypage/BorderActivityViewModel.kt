package com.example.klab2challenge.ui.mypage

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

    fun requestUser(userName: String) {
        viewModelScope.launch {
            userRepository.requestUser(userName)
        }
    }

    fun requestBorder(userName: String) {
        viewModelScope.launch {
            borderRepository.requestBorder(userName)
        }
    }

    fun requestChangeBorder(checkedBorder: Int) {
        viewModelScope.launch {
            val userInfo = users.value!!.get(0)
            val firstTask = async {
                userRepository.requestChangeBorder(userInfo.name, checkedBorder)
            }
            firstTask.await()
            delay(100)
            val secondTask = async {
                rankingRepository.refreshRanking(userInfo.name)
            }
            secondTask.await()
        }
    }


    fun requestBuyBorder(checkedBorder: Int) {
        viewModelScope.launch {
            val userInfo = users.value!!.get(0)
            val borderInfo = borders.value!!.get(checkedBorder)
            val firstTask = async {
                userRepository.requestBuyBorder(userInfo.name, checkedBorder)
            }
            firstTask.await()
            delay(100)
            val secondTask = async {
                borderRepository.updateIsunlocked(checkedBorder)
            }
            secondTask.await()
            delay(100)
            val thirdTask = async {
                userRepository.requestSetCoin(userInfo.name, userInfo.currentCoin, borderInfo.price)
            }
            thirdTask.await()
        }
    }
}