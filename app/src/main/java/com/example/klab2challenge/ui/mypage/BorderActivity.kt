package com.example.klab2challenge.ui.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.klab2challenge.databinding.ActivityBorderBinding

class BorderActivity : AppCompatActivity() {
    lateinit var binding : ActivityBorderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}