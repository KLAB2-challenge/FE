package com.example.klab2challenge.ui.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.ActivityRecordDetailBinding
import com.example.klab2challenge.retrofit.GetProofPostResponse
import com.example.klab2challenge.retrofit.GetProofPostsRequest
import com.example.klab2challenge.retrofit.RetrofitUtil
import retrofit2.Callback

class RecordDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityRecordDetailBinding
    private val commentViewModel = CommentViewModel()
    private val recordDetailViewModel = RecordDetailViewModel()
    private val recordId = intent.getIntExtra("recordId", -1)


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

        binding.cvRdBackBtn.setOnClickListener {
            finish()
        }

        recordDetailViewModel.recordDetail.observe(this, Observer {
            binding.tvRdRecordTitle.text = it.title
            binding.tvRdRecordContent.text = it.content
        })

//        val proofRequest = GetProofPostRequest("user1", recordDetailId)
//        RetrofitUtil.getRetrofitUtil().getProofPost(proofRequest).enqueue(object : Callback<GetProofPostResponse> {
//
//        })
    }
}