package com.example.klab2challenge.ui.mychallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.db.MCPEntity
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.GetChallengeResponse

class MyChallengeViewModel : ViewModel() {
    private var list = ArrayList<MCPEntity>();
    private var _itemList = MutableLiveData<ArrayList<MCPEntity>>();

    val itemList: LiveData<ArrayList<MCPEntity>> get() = _itemList

    init {
        list = arrayListOf()

        _itemList.value = list
    }

    fun setMyChallenge(cList: List<MCPEntity>) {
        list.clear()
        list.addAll(cList)

        _itemList.value = list
    }
}