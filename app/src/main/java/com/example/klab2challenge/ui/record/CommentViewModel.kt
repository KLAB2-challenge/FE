package com.example.klab2challenge.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klab2challenge.retrofit.GetCommentResponse

class CommentViewModel : ViewModel() {
    private var list = ArrayList<GetCommentResponse>()
    private val _commentList = MutableLiveData<List<GetCommentResponse>>()

    val commentList : LiveData<List<GetCommentResponse>> get() = _commentList

    init {
        list = arrayListOf()
        _commentList.value = list
    }

    fun setComments(comments : List<GetCommentResponse>) {
        list.clear()
        list.addAll(comments)
        _commentList.value = list
    }

}