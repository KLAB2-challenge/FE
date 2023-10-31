package com.example.klab2challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecordViewModel : ViewModel() {
    private var list = ArrayList<String>()
    private val _itemList = MutableLiveData<List<String>>()
    val itemList: LiveData<List<String>> get() = _itemList

    init {
        list = arrayListOf("1", "1", "1", "1", "1", "1", "1")
        _itemList.value = list
    }
}