package com.example.klab2challenge.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.repository.BorderRepository
import com.example.klab2challenge.db.repository.RankingRepository
import com.example.klab2challenge.db.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BorderActivityViewModel(
    private val userRepository: UserRepository,
    private val borderRepository: BorderRepository,
    private val rankingRepository: RankingRepository
) : ViewModel() {
    val borders = borderRepository.borders.asLiveData()
    val users = userRepository.users.asLiveData()
    val rankings = rankingRepository.rankings.asLiveData()
    val _checkedItem = MutableLiveData<Int>()
    val checkedItem : LiveData<Int> get() = _checkedItem
    fun checkItem(item : Int) : Boolean {
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
        val userInfo = users.value!!.get(0)
        viewModelScope.launch {
            userRepository.requestChangeBorder(userInfo.name, checkedBorder)
            delay(1000)
            rankingRepository.refreshRanking(userInfo.name)
        }
    }

    fun requestBuyBorder(checkedBorder: Int) {
        val userInfo = users.value!!.get(0)
        val borderInfo = borders.value!!.get(checkedBorder)
        viewModelScope.launch {
            userRepository.requestBuyBorder(userInfo.name, checkedBorder)
            delay(1000)
            borderRepository.updateIsunlocked(checkedBorder)
            delay(1000)
            userRepository.requestSetCoin(userInfo.name, userInfo.currentCoin, borderInfo.price)
        }
    }


}