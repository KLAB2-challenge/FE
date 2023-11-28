package com.example.klab2challenge.ui.mypage

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BorderOptionsViewModel() : ViewModel() {
    private var list = ArrayList<BorderOption>()
    private val _itemlist = MutableLiveData<List<BorderOption>>()
    val itemList: LiveData<List<BorderOption>> get() = _itemlist


    fun setItems(borderOptionList: List<BorderOption>) {
        list.addAll(borderOptionList)

        _itemlist.value = list
    }

    fun clickItem(id: Int) {
        for (b in list) {
            b.isChecked = b.ID == id
        }

        _itemlist.value = list
    }

    fun getSelectBtnVisibility(): Int {
        for (b in list) {
            if (b.isChecked && b.isLocked)
                return View.GONE
            else
                break
        }
        return View.VISIBLE
    }

    fun getReleaseBtnVisibility() : Int {
        if(getSelectBtnVisibility() == View.GONE)
            return View.VISIBLE
        else
            return View.GONE
    }

    fun getCheckedItem() : BorderOption? {
        for(b in list) {
            if(b.isChecked)
                return b
        }
        return null
    }

    fun unlockItem(id : Int) {
        for(b in list) {
            if(b.ID == id)
                b.isLocked = false
        }
    }
}