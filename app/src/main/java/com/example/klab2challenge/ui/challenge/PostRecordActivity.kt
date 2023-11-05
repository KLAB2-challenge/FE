package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityPostRecordBinding

class PostRecordActivity : AppCompatActivity() {
    lateinit var binding : ActivityPostRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvPrPostBtn.setOnClickListener {
            finish()
            val i = Intent(applicationContext, RecordListActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
        }

        binding.cvPrBackBtn.setOnClickListener {
            finish()
        }
    }
}