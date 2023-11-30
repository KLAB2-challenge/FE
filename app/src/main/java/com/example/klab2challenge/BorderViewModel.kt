package com.example.klab2challenge

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.klab2challenge.db.BorderEntity
import com.example.klab2challenge.db.BorderRepository
import com.example.klab2challenge.ui.mypage.BorderOption
import kotlinx.coroutines.launch

class BorderViewModel(private val borderRepository: BorderRepository) : ViewModel() {
    val borders = borderRepository.borders.asLiveData()

    @WorkerThread
    fun insert(border : BorderEntity) {
        viewModelScope.launch {
            borderRepository.insert(border)
        }
    }
    fun requestBorder(userName: String) {
        viewModelScope.launch {
            borderRepository.requestBorder(userName)
        }
    }

//    fun setItems(borderOptionList: List<BorderOption>) {
//        list.addAll(borderOptionList)
//
//        _itemlist.value = list
//    }
//
    @WorkerThread
    fun clickItem(id: Int) {
        for (b in borders.value!!) {
            b.isChecked = b.number == id
        }
    }

    fun getSelectBtnVisibility(): Int {
        for (b in borders.value!!) {
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
//
//    fun getCheckedItem() : BorderOption? {
//        for(b in list) {
//            if(b.isChecked)
//                return b
//        }
//        return null
//    }
//
//    fun unlockItem(id : Int) {
//        for(b in list) {
//            if(b.ID == id)
//                b.isLocked = false
//        }
//    }
}