package com.example.klab2challenge.ui.mypage

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityBorderBinding
import com.example.klab2challenge.ui.mychallenge.BorderAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BorderActivity : AppCompatActivity() {
    lateinit var binding: ActivityBorderBinding
    private val borderActivityViewModel: BorderActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBorder.layoutManager = GridLayoutManager(this, 2)
        val borderAdapter = BorderAdapter()
        borderAdapter.itemClickListener = object : BorderAdapter.OnItemClickListener {
            override fun onItemClicked(checkedItem: Int) {
                val isUnlocked = borderActivityViewModel.checkItem(checkedItem)
                binding.cvBSelectBtn.visibility = if (isUnlocked) View.VISIBLE else View.GONE
                binding.cvBReleaseBtn.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                val borderInfo = borderActivityViewModel.borders.value!!.get(checkedItem)
                val userInfo = borderActivityViewModel.users.value!!.get(0)

                val isEnable = borderInfo.price <= userInfo.currentCoin
                val bk = getColor(R.color.BK)
                val gs = getColor(R.color.GS_60)
                binding.cvBReleaseBtn.isEnabled = isEnable
                binding.cvBReleaseBtn.strokeColor = if(isEnable) bk else gs
                binding.tvBReleaseBtn.setTextColor(if(isEnable) bk else gs)


                binding.tvBNoticeLabel.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                binding.tvBNoticeLabel2.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                binding.tvBNoticeCoin.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                binding.tvBNoticeCoin.text = borderInfo.price.toString()

                binding.tvBWarningLabel.visibility = if (!isUnlocked && !isEnable) View.VISIBLE else View.GONE
                binding.tvBWarningLabel2.visibility = if (!isUnlocked && !isEnable) View.VISIBLE else View.GONE
                binding.tvBWarningCoin.visibility = if (!isUnlocked && !isEnable) View.VISIBLE else View.GONE
                binding.tvBWarningCoin.text = (borderInfo.price - userInfo.currentCoin).toString()
            }
        }
        binding.rvBorder.adapter = borderAdapter
        borderActivityViewModel.borders.observe(this, Observer {
            (binding.rvBorder.adapter as BorderAdapter).setData(it)
            borderActivityViewModel.users.observe(this, Observer {
                if(it.isEmpty())
                    return@Observer
                Log.d("hiuser", it.toString())
                val userInfo = it[0]
                val borderList = borderActivityViewModel.borders.value!!
                if(borderList.size < 6)
                    return@Observer


                binding.tvBMyCoin.text = userInfo.currentCoin.toString()
                binding.ifbBProfileBorder.backgroundTintList =
                    ColorStateList.valueOf(borderList[userInfo.currentBorder].color)
                Log.d("hasdfasdf", borderActivityViewModel.checkedItem.value.toString())
                if(borderActivityViewModel.checkedItem.value == null)
                    return@Observer
                val checkedItem = borderActivityViewModel.checkedItem.value!!
                val isUnlocked = borderActivityViewModel.checkItem(checkedItem)
                binding.cvBSelectBtn.visibility = if (isUnlocked) View.VISIBLE else View.GONE
                binding.cvBReleaseBtn.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                val borderInfo = borderActivityViewModel.borders.value!!.get(checkedItem)

                val isEnable = borderInfo.price <= userInfo.currentCoin
                val bk = getColor(R.color.BK)
                val gs = getColor(R.color.GS_60)
                binding.cvBReleaseBtn.isEnabled = isEnable
                binding.cvBReleaseBtn.strokeColor = if(isEnable) bk else gs
                binding.tvBReleaseBtn.setTextColor(if(isEnable) bk else gs)


                binding.tvBNoticeLabel.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                binding.tvBNoticeLabel2.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                binding.tvBNoticeCoin.visibility = if (!isUnlocked) View.VISIBLE else View.GONE
                binding.tvBNoticeCoin.text = borderInfo.price.toString()

                binding.tvBWarningLabel.visibility = if (!isUnlocked && !isEnable) View.VISIBLE else View.GONE
                binding.tvBWarningLabel2.visibility = if (!isUnlocked && !isEnable) View.VISIBLE else View.GONE
                binding.tvBWarningCoin.visibility = if (!isUnlocked && !isEnable) View.VISIBLE else View.GONE
                binding.tvBWarningCoin.text = (borderInfo.price - userInfo.currentCoin).toString()
            })
        })

        borderActivityViewModel.checkedItem.observe(this, Observer {
            (binding.rvBorder.adapter as BorderAdapter).setCheckedItem(it)
        })

        binding.cvBSelectBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("borderActivity", borderActivityViewModel.checkedItem.value!!.toString())
                borderActivityViewModel.requestChangeBorder(borderActivityViewModel.checkedItem.value!!)
            }
        }

        binding.cvBReleaseBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                borderActivityViewModel.requestBuyBorder(borderActivityViewModel.checkedItem.value!!, this@BorderActivity)
            }
        }

        binding.cvBBackBtn.setOnClickListener {
            finish()
        }
    }
}


