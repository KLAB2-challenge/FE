package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.ActivityRecordListBinding
import com.example.klab2challenge.retrofit.GetProofPostsRequest
import com.example.klab2challenge.retrofit.GetProofPostsResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordListActivity : AppCompatActivity() {
    lateinit var _binding : ActivityRecordListBinding
    private val binding : ActivityRecordListBinding get() = _binding
    private val recordViewModel = RecordViewModel()
    private var challengeId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecordListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        challengeId = intent.getIntExtra("challengeId", -1)

        initLayout()

        RetrofitUtil.getRetrofitUtil().getProofPosts(challengeId).enqueue(object : Callback<GetProofPostsResponse> {
            override fun onResponse(
                call: Call<GetProofPostsResponse>,
                response: Response<GetProofPostsResponse>
            ) {
                if(response.isSuccessful) {
                    recordViewModel.addRecords(response.body()!!.proofPosts)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetProofPostsResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }
        })
    }

    fun initLayout() {
        binding.rvRl.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = RecordListAdapter(applicationContext, recordViewModel.itemList.value!!)
        adapter.setOnItemClickListener(object : RecordListAdapter.OnItemClickListener {
            override fun onItemClicked(recordId: Int) {
                val i = Intent(applicationContext, RecordDetailActivity::class.java)
                i.putExtra("recordId", recordId)
                startActivity(i)
            }
        })
        binding.rvRl.adapter = adapter
        recordViewModel.itemList.observe(this, Observer {
            (binding.rvRl.adapter as RecordListAdapter).setData(it)
        })

        binding.cvRlBackBtn.setOnClickListener {
            finish()
        }
        binding.cvRlPostBtn.setOnClickListener {
            val i = Intent(applicationContext, PostRecordActivity::class.java)
            i.putExtra("challengeId", challengeId)
            startActivity(i)
        }
    }
}