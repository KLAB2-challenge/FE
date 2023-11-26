package com.example.klab2challenge.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityBorderBinding

class BorderActivity : AppCompatActivity() {
    lateinit var binding: ActivityBorderBinding

    private lateinit var border: ArrayList<View>
    private lateinit var icCheck: ArrayList<View>
    private var borderCoin = arrayListOf(10,20,30,40,50,60)
    private var isChecked = arrayListOf(false, false, false, false, false, false)
    private var isUnlocked = arrayListOf(false, false, false, false, false, false)
    lateinit var color: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        color = arrayListOf(
            getColor(R.color.gold),
            getColor(R.color.green),
            getColor(R.color.cherry),
            getColor(R.color.blueberry),
            getColor(R.color.sunny),
            getColor(R.color.rainy)
        )

        border = arrayListOf(
            binding.phBGold,
            binding.phBGreen,
            binding.phBCherry,
            binding.phBBlueberry,
            binding.phBSunny,
            binding.phBRainy
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
        Log.d("chaerin", "클릭")
        for (i in 0..5){
            border[i].setOnClickListener {
                borderChecking(i)
                showNotice(i)
            }
        }
    }

    private fun showNotice(i: Int) {
        if (!isUnlocked[i]) {
            binding.tvBNoticeLabel.visibility = View.VISIBLE
            binding.tvBNoticeLabel2.visibility = View.VISIBLE
            binding.tvBNoticeCoin.text = borderCoin[i].toString()
            binding.tvBNoticeCoin.visibility = View.VISIBLE

            //내 코인수와 비교했을 때 구매 불가능이면 빨간색 띄우기
            var needCoin = borderCoin[i] - 10 //10에 내 코인 넣기
            if (needCoin < 0){
                binding.tvBWarningLabel.visibility = View.VISIBLE
                binding.tvBNoticeLabel2.visibility = View.VISIBLE
                binding.tvBWarningCoin.text = needCoin.toString()
                binding.tvBWarningCoin.visibility = View.VISIBLE
            }

            binding.cvBBtn.strokeColor = getColor(R.color.BK)
            binding.tvBBtn.text = "Release"
        }
        else {
            binding.tvBNoticeLabel.visibility = View.GONE
            binding.tvBNoticeCoin.visibility = View.GONE
            binding.tvBNoticeLabel2.visibility = View.GONE

            binding.tvBWarningLabel.visibility = View.GONE
            binding.tvBNoticeLabel2.visibility = View.GONE
            binding.tvBWarningCoin.visibility = View.GONE

            binding.cvBBtn.strokeColor = getColor(R.color.BK)
            binding.tvBBtn.text = "Select"
        }
    }

    private fun borderChecking(i: Int) {
        for (i in 0..5 step(1)){
            icCheck[i].visibility = View.GONE
            isChecked[i] = false
        }

        if (!isChecked[i]) {
            icCheck[i].visibility = View.VISIBLE
            binding.ifbBProfileBorder.setBackgroundColor(color[i])
            isChecked[i] = true

        } else {
            icCheck[i].visibility = View.GONE
            binding.ifbBProfileBorder.setBackgroundColor(getColor(R.color.WH))
            isChecked[i] = false

            binding.cvBBtn.strokeColor = getColor(R.color.GS_60)
            binding.tvBBtn.text = "Select"
        }
    }
}