package com.example.klab2challenge.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RankingViewModel : ViewModel() {
    private var list = ArrayList<String>()
    private val _itemlist = MutableLiveData<String>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}