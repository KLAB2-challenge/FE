package com.example.klab2challenge.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityBorderBinding

class BorderActivity : AppCompatActivity() {
    lateinit var binding : ActivityBorderBinding

    private lateinit var border : ArrayList<View>
    private lateinit var icCheck : ArrayList<View>
    private var isChecked = arrayListOf(false, false, false, false, false, false)
    private var isUnlocked = arrayListOf(false, false, false, false, false, false)
    private var color = arrayListOf(
        getColor(R.color.gold), getColor(R.color.green), getColor(R.color.cherry),
        getColor(R.color.blueberry), getColor(R.color.sunny), getColor(R.color.rainy)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        border = arrayListOf(
            binding.clBGoldContainer, binding.clBGreenContainer, binding.clBCherryContainer,
            binding.clBBlueberryContainer,binding.clBSunnyContainer, binding.clBRainyContainer
        )

        icCheck = arrayListOf(
            binding.ivBGoldCheck, binding.ivBGreenCheck, binding.ivBCherryCheck,
            binding.ivBBlueberryCheck, binding.ivBSunnyCheck, binding.ivBRainyCheck
        )

        binding.cvBBackBtn.setOnClickListener {
            finish()
        }

        startClickListener() //setOnClickListener 추가


    }

    private fun startClickListener() {
        for (i in 0..5 step(1)) {
            border[i].setOnClickListener {
                if (!isChecked[i]) {
                    icCheck[i].visibility = View.VISIBLE
                    binding.ifbBProfileBorder.setBackgroundColor(color[i])
                    isChecked[i] = true
                } else {
                    icCheck[i].visibility = View.GONE
                    binding.ifbBProfileBorder.setBackgroundColor(getColor(R.color.WH))
                    isChecked[i] = false
                }

                if (!isUnlocked[i]) {
                    binding.tvBNoticeLabel.visibility = View.VISIBLE
                    binding.tvBNoticeCoin.visibility = View.VISIBLE
                    binding.tvBNoticeLabel2.visibility = View.VISIBLE
                    binding.tvBNoticeCoin.setText("코인수")
                } else {
                    binding.tvBNoticeLabel.visibility = View.GONE
                    binding.tvBNoticeCoin.visibility = View.GONE
                    binding.tvBNoticeLabel2.visibility = View.GONE
                }
            }
        }
    }
}