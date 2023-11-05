package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityChallengeDetailBinding
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.GetChallengeRequest
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetRelatedChallengesRequest
import com.example.klab2challenge.retrofit.GetRelatedChallengesResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.ui.home.ChallengeAdapter
import com.example.klab2challenge.ui.home.ChallengeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityChallengeDetailBinding
    private val challengeViewModel = ChallengeViewModel()

    val binding : ActivityChallengeDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val challengeId = intent.getIntExtra("challengeId", -1)

        binding.rvCd.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ChallengeAdapter(challengeViewModel.itemList.value!!)
        adapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(applicationContext, ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvCd.adapter = adapter
        challengeViewModel.itemList.observe(this, Observer {
            (binding.rvCd.adapter as ChallengeAdapter).setData(it)
        })
        binding.cvRlBackBtn.setOnClickListener {
            finish()
        }

        binding.cvRlJoinBtn.setOnClickListener {
            binding.cvRlPostBtn.visibility = View.VISIBLE
            binding.cvRlPostBtn.setOnClickListener {
                val i = Intent(applicationContext, PostRecordActivity::class.java)
                startActivity(i)
            }
            it.visibility = View.GONE
        }

        binding.tvCdMore.setOnClickListener {
            val i = Intent(applicationContext, RecordListActivity::class.java)
            startActivity(i)
        }


        binding.ivCdMore.setOnClickListener {
            val i = Intent(applicationContext, RecordListActivity::class.java)
            startActivity(i)
        }

        val challengeRequest = GetChallengeRequest("user1", challengeId)
        RetrofitUtil.getRetrofitUtil().getChallenge(challengeRequest).enqueue(object : Callback<GetChallengeResponse> {
            override fun onResponse(
                call: Call<GetChallengeResponse>,
                response: Response<GetChallengeResponse>
            ) {
                if(response.isSuccessful) {
                    val data = response.body()
                    val content = data!!.contents
                } else {
                    Log.d("hyunhee", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetChallengeResponse>, t: Throwable) {
                Log.d("hyunhee", t.message.toString())
            }

        })

        val relatedRequest = GetRelatedChallengesRequest("user1",0,5,1)
        RetrofitUtil.getRetrofitUtil().getChallenge(relatedRequest).enqueue(object: Callback<GetRelatedChallengesResponse> {
            override fun onResponse(
                call: Call<GetRelatedChallengesResponse>,
                response: Response<GetRelatedChallengesResponse>
            ) {
                if(response.isSuccessful) {
                    challengeViewModel.addChallenges(response.body()!!.challenges)
                } else {
                    Log.d("seohyun", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<GetRelatedChallengesResponse>, t: Throwable) {
                Log.d("seohyun", t.message.toString())
            }

        })
    }
}