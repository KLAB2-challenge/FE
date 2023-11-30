package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.klab2challenge.databinding.ActivityChallengeDetailBinding

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityChallengeDetailBinding
//    private val challengeViewModel = HCPViewModel()
    private val challengeDetailViewModel = ChallengeDetailViewModel()
    private val recordViewModel = RecordViewModel()
    private var challengeId = -1

    val binding : ActivityChallengeDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        challengeId = intent.getIntExtra("challengeId", -1)
//        initLayout()
//
//        val challengeRequest = GetChallengeRequest(getUserName(this), challengeId)
//        RetrofitUtil.getRetrofitUtil().getChallenge(challengeRequest).enqueue(object : Callback<GetChallengeResponse> {
//            override fun onResponse(
//                call: Call<GetChallengeResponse>,
//                response: Response<GetChallengeResponse>
//            ) {
//                if(response.isSuccessful) {
//                    println(response.body()!!.toString())
//                    challengeDetailViewModel.setChallengeDetail(response.body()!!)
//                    Glide.with(this@ChallengeDetailActivity).load(response.body()!!.contents.image).into(binding.ivCdImage)
//                } else {
//                    Log.d("hyunhee", response.errorBody().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<GetChallengeResponse>, t: Throwable) {
//                Log.d("hyunhee", t.message.toString())
//            }
//        })
//
//        RetrofitUtil.getRetrofitUtil().getProofPosts(challengeId).enqueue(object : Callback<GetProofPostsResponse> {
//            override fun onResponse(
//                call: Call<GetProofPostsResponse>,
//                response: Response<GetProofPostsResponse>
//            ) {
//                if(response.isSuccessful) {
//                    var postList = response.body()!!.proofPosts
//                    recordViewModel.addRecords(postList)
//                    if(postList.size >= 1) {
//                        binding.tvCdRecordTitle1.text = postList.get(0).contents.title
//                        binding.tvCdRecordContent1.text = postList.get(0).contents.content
//                        Glide.with(this@ChallengeDetailActivity).load(postList.get(0).contents.image).into(binding.ivCdRecord1Image)
//                    }
//                    if(postList.size >= 2) {
//                        binding.tvCdRecordTitle2.text = postList.get(1).contents.title
//                        binding.tvCdRecordContent2.text = postList.get(1).contents.content
//                        Glide.with(this@ChallengeDetailActivity).load(postList.get(1).contents.image).into(binding.ivCdRecord2Image)
//                    }
//                } else {
//                    Log.d("hyunhee", response.errorBody()!!.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<GetProofPostsResponse>, t: Throwable) {
//                Log.d("hyunhee", t.message.toString())
//            }
//
//        })
//
//        val relatedRequest = GetRelatedChallengesRequest(getUserName(this),0,5,1)
//        RetrofitUtil.getRetrofitUtil().getChallenge(relatedRequest).enqueue(object: Callback<GetRelatedChallengesResponse> {
//            override fun onResponse(
//                call: Call<GetRelatedChallengesResponse>,
//                response: Response<GetRelatedChallengesResponse>
//            ) {
//                if(response.isSuccessful) {
//                    challengeViewModel.addHCP(response.body()!!.challenges)
//                } else {
//                    Log.d("seohyun", response.errorBody().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<GetRelatedChallengesResponse>, t: Throwable) {
//                Log.d("seohyun", t.message.toString())
//            }
//
//        })
//    }
//
//    fun initLayout() {
//        binding.cvRlBackBtn.setOnClickListener {
//            finish()
//        }
//
//        binding.cvRlJoinBtn.setOnClickListener {
//            val joinRequest = JoinChallengeRequest(getUserName(this), challengeId)
//            RetrofitUtil.getRetrofitUtil().setChallenge(joinRequest).enqueue(object : Callback<JoinChallengeResponse> {
//                override fun onResponse(
//                    call: Call<JoinChallengeResponse>,
//                    response: Response<JoinChallengeResponse>
//                ) {
//                    if(response.isSuccessful) {
//                        challengeDetailViewModel.setChallengeDetailJoin(true)
//                    } else {
//                        Log.d("seohyun", response.errorBody().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<JoinChallengeResponse>, t: Throwable) {
//                    Log.d("seohyun", t.message.toString())
//                }
//            })
//        }
//
//        binding.cvRlPostBtn.setOnClickListener {
//            val i = Intent(applicationContext, PostRecordActivity::class.java)
//            i.putExtra("challengeId", challengeId)
//            startActivity(i)
//        }
//
//        binding.tvCdMore.setOnClickListener {
//            val i = Intent(applicationContext, RecordListActivity::class.java)
//            i.putExtra("challengeId", challengeId)
//            startActivity(i)
//        }
//
//        binding.ivCdMore.setOnClickListener {
//            val i = Intent(applicationContext, RecordListActivity::class.java)
//            i.putExtra("challengeId", challengeId)
//            startActivity(i)
//        }
//
//
//        binding.rvCd.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        val adapter = HCPAdapter(this, challengeViewModel.itemList.value!!)
//        adapter.itemClickListener = object : HCPAdapter.OnItemClickListener {
//            override fun onItemClicked(challengeId : Int) {
//                val i = Intent(applicationContext, ChallengeDetailActivity::class.java)
//                i.putExtra("challengeId", challengeId)
//                startActivity(i)
//            }
//        }
//        binding.rvCd.adapter = adapter
//        challengeViewModel.itemList.observe(this, Observer {
//            (binding.rvCd.adapter as HCPAdapter).setData(it)
//        })
//
//        challengeDetailViewModel.challengeDetail.observe(this, Observer {
//            binding.tvCdTitle.text = it.contents.title
//            binding.tvCdContent.text = it.contents.content
//            binding.tvCdDuration.text = it.infos.startDate + " ~ " + it.infos.endDate + "\n" + it.infos.frequency
//            if(it.join){
//                binding.cvRlJoinBtn.visibility = View.GONE
//                binding.cvRlPostBtn.visibility = View.VISIBLE
//            } else {
//                binding.cvRlJoinBtn.visibility = View.VISIBLE
//                binding.cvRlPostBtn.visibility = View.GONE
//            }
//        })
//
//        recordViewModel.itemList.observe(this, Observer {
//            println(it.size)
//            if(it.size >= 2) {
//                binding.tvCdRecordTitle1.text = it[0].contents.title
//                binding.tvCdRecordContent1.text = it[0].contents.content
//                binding.tvCdRecordTitle2.text = it[1].contents.title
//                binding.tvCdRecordContent2.text = it[1].contents.content
//            } else if(it.size == 1) {
//                binding.tvCdRecordTitle1.text = it[0].contents.title
//                binding.tvCdRecordContent1.text = it[0].contents.content
//            }
//        })
    }
}