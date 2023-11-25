package com.example.klab2challenge.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.klab2challenge.databinding.ActivityBorderBinding

class BorderActivity : AppCompatActivity() {
    lateinit var binding : ActivityBorderBinding
    private var isChecked = intArrayOf(0,0,0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvBBackBtn.setOnClickListener {
            finish()
        }

        binding.clBGoldContainer.setOnClickListener {
            if (isChecked[0]==0) {
                binding.ivBGoldCheck.visibility = View.VISIBLE
            }
        }
    }
}