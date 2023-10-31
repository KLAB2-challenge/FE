package com.example.klab2challenge.ui.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityChallengeDetailBinding

class ChallengeDetailActivity : AppCompatActivity() {
    lateinit var _binding : ActivityChallengeDetailBinding
    private val relatedChallenViewModel = RelatedChallengeViewModel()

    val binding : ActivityChallengeDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChallengeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCd.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCd.adapter = RelatedChallengeAdapter(relatedChallenViewModel.itemList.value!!)
        relatedChallenViewModel.itemList.observe(this, Observer {
            (binding.rvCd.adapter as RelatedChallengeAdapter).setData(it)
        })
        binding.cvRlBackBtn.setOnClickListener {
            finish()
        }
    }
}