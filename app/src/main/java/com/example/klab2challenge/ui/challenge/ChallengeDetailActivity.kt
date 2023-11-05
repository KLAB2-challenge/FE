package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityChallengeDetailBinding
import com.example.klab2challenge.ui.home.ChallengeAdapter
import com.example.klab2challenge.ui.home.ChallengeViewModel

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityChallengeDetailBinding
    private val challenViewModel = ChallengeViewModel()

    val binding : ActivityChallengeDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCd.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ChallengeAdapter(challenViewModel.itemList.value!!)
        adapter.itemClickListener = object : ChallengeAdapter.OnItemClickListener {
            override fun onItemClicked() {
                val i = Intent(applicationContext, ChallengeDetailActivity::class.java)
                startActivity(i)
            }
        }
        binding.rvCd.adapter = adapter
        challenViewModel.itemList.observe(this, Observer {
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
    }
}