package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.ActivityChallengeDetailBinding
import com.example.klab2challenge.retrofit.GetChallengeRequest
import com.example.klab2challenge.retrofit.GetRelatedChallengesRequest
import com.example.klab2challenge.retrofit.JoinChallengeRequest
import com.example.klab2challenge.ui.home.HCPAdapter
import com.example.klab2challenge.ui.record.AddRecordActivity
import com.example.klab2challenge.ui.record.RecordListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityChallengeDetailBinding
    private val challengeDetailViewModel : ChallengeDetailViewModel by viewModel()
    private var challengeId = -1

    val binding : ActivityChallengeDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        challengeId = intent.getIntExtra("challengeId", -1)

        initDefaultLayout()
        initRelatedChallengeAdapter()
        initObservers()
    }

    private fun initObservers() {
        challengeDetailViewModel.challengeDetailInfo.observe(this, Observer {
            binding.tvCdTitle.text = it.contents.title
            binding.tvCdContent.text = it.contents.content
            binding.tvCdDuration.text = it.infos.startDate + " ~ " + it.infos.endDate + "\n" + it.infos.frequency
            if(it.join){
                binding.cvRlJoinBtn.visibility = View.GONE
                binding.cvRlPostBtn.visibility = View.VISIBLE
            } else {
                binding.cvRlJoinBtn.visibility = View.VISIBLE
                binding.cvRlPostBtn.visibility = View.GONE
            }
        })


        challengeDetailViewModel.records.observe(this, Observer {
            if(it.size >= 1) {
                binding.tvCdRecordTitle1.text = it[0].title
                binding.tvCdRecordContent1.text = it[0].description
            }
            if(it.size >= 2) {
                binding.tvCdRecordTitle2.text = it[1].title
                binding.tvCdRecordContent2.text = it[1].description
            }
        })


        challengeDetailViewModel.users.observe(this, Observer {
            val userName = challengeDetailViewModel.users.value!!.get(0).name
            challengeDetailViewModel.requestChallengeDetail(GetChallengeRequest(userName, challengeId))
            challengeDetailViewModel.requestRelatedChallenges(GetRelatedChallengesRequest(userName, 0, 5, 0))

            binding.cvRlJoinBtn.setOnClickListener {
                challengeDetailViewModel.requestJoin(JoinChallengeRequest(userName, challengeId))
            }
        })
        challengeDetailViewModel.requestProofPosts(challengeId)
    }

    private fun initRelatedChallengeAdapter() {
        binding.rvCd.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = HCPAdapter(this)
        adapter.itemClickListener = object : HCPAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId : Int) {
                val i = Intent(applicationContext, ChallengeDetailActivity::class.java)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        }
        binding.rvCd.adapter = adapter
        challengeDetailViewModel.relatedChallenges.observe(this, Observer {
            (binding.rvCd.adapter as HCPAdapter).setData(it)
        })
    }

    private fun initDefaultLayout() {
        binding.cvRlBackBtn.setOnClickListener {
            finish()
        }

        binding.cvRlPostBtn.setOnClickListener {
            val i = Intent(applicationContext, AddRecordActivity::class.java)
            i.putExtra("challengeId", challengeId)
            startActivity(i)
        }

        binding.tvCdMore.setOnClickListener {
            val i = Intent(applicationContext, RecordListActivity::class.java)
            i.putExtra("challengeId", challengeId)
            startActivity(i)
        }

        binding.ivCdMore.setOnClickListener {
            val i = Intent(applicationContext, RecordListActivity::class.java)
            i.putExtra("challengeId", challengeId)
            startActivity(i)
        }
    }
}