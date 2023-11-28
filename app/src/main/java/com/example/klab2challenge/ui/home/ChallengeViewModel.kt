package com.example.klab2challenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.klab2challenge.db.HCPEntity

class ChallengeViewModel {
    private var list = ArrayList<HCPEntity>()
    private val _itemList = MutableLiveData<List<HCPEntity>>()
    val itemList: LiveData<List<HCPEntity>> get() = _itemList


    init {
        list = arrayListOf()

        _itemList.value = list
    }

    fun setChallenges(challengeList: List<HCPEntity>) {
        list.clear()
        list.addAll(challengeList)

        _itemList.value = list
    }

    fun addChallenge(challenge: HCPEntity) {
        list.add(challenge)

        _itemList.value = list
    }

    fun addChallenges(challenges: List<HCPEntity>) {
        list.addAll(challenges)

        _itemList.value = list
    }

    fun deleteChallenge(challenge: HCPEntity) {
        list.remove(challenge)

        _itemList.value = list
    }

    fun clearChallenge() {
        list.clear()

        _itemList.value = list
    }
}