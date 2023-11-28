package com.example.klab2challenge.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.db.RankingEntity
import com.example.klab2challenge.retrofit.Member
import com.example.klab2challenge.retrofit.MemberInfos

class RankingViewModel : ViewModel() {
    private var list = ArrayList<RankingEntity>()
    private val _itemlist = MutableLiveData<List<RankingEntity>>()
    val itemList: LiveData<List<RankingEntity>> get() = _itemlist

    init {
        list = arrayListOf<RankingEntity>()

        _itemlist.value = list
    }

    fun setItem(rankingList: List<RankingEntity>) {
        var al = arrayListOf<RankingEntity>()
        al.addAll(rankingList)
        list = al

        _itemlist.value = list
    }
}