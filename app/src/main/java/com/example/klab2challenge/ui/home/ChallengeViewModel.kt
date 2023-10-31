package com.example.klab2challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ChallengeViewModel {
    private var list = ArrayList<String>()
    private val _itemList = MutableLiveData<List<String>>()

    val itemList: LiveData<List<String>> get() = _itemList

    init {
        list = arrayListOf()
        _itemList.value = list
    }
    fun addChallenge(str : String) {
        list.add(str)

        _itemList.value = list
    }

    fun deleteChallenge(str : String) {
        list.remove(str)

        _itemList.value = list
    }
}