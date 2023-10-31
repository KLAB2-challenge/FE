package com.example.klab2challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RelatedChallengeViewModel {
    private var list = ArrayList<String>()
    private val _itemList = MutableLiveData<List<String>>()

    val itemList: LiveData<List<String>> get() = _itemList

    init {
        list = arrayListOf("1","1","1","1","1","1","1")
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