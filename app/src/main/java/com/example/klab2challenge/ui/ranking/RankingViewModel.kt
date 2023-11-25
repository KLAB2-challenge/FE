package com.example.klab2challenge.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.retrofit.Member
import com.example.klab2challenge.retrofit.MemberInfos

class RankingViewModel : ViewModel() {
    private var list = ArrayList<Member>()
    private val _itemlist = MutableLiveData<List<Member>>()
    val itemList: LiveData<List<Member>> get() = _itemlist

    init {
        list = arrayListOf<Member>(
//            Member(
//                0,
//                "user1",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            ),
//            Member(
//                1,
//                "user2",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            ),
//            Member(
//                2,
//                "user3",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            ),
//            Member(
//                3,
//                "user4",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            ),
//            Member(
//                4,
//                "user5",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            ),
//            Member(
//                5,
//                "user6",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            ),
//            Member(
//                6,
//                "user7",
//                MemberInfos(0, 0, 0, ""),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf(),
//                listOf()
//            )
        )

        _itemlist.value = list
    }

    fun setItem(rankingList: List<Member>) {
        var al = arrayListOf<Member>()
        al.addAll(rankingList)
        list = al

        _itemlist.value = list
    }
}