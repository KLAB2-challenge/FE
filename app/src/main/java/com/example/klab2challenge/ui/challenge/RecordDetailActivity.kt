package com.example.klab2challenge.ui.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.ActivityRecordDetailBinding

class RecordDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityRecordDetailBinding
    private val commentViewModel = CommentViewModel()

    val binding : ActivityRecordDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvRdComments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRdComments.adapter = CommentAdapter(commentViewModel.itemList.value!!)
        commentViewModel.itemList.observe(this, Observer {
            (binding.rvRdComments.adapter as CommentAdapter).setData(it)
        })


    }
}