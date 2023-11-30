package com.example.klab2challenge.ui.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.BorderViewModel
import com.example.klab2challenge.databinding.ActivityBorderBinding
import com.example.klab2challenge.db.MyDatabase
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.ui.mychallenge.BorderAdapter
import com.example.klab2challenge.ui.ranking.RankingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BorderActivity : AppCompatActivity() {
    lateinit var binding: ActivityBorderBinding
    private val borderViewModel : BorderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBorder.layoutManager = GridLayoutManager(this, 2)
        val borderAdapter = BorderAdapter()
        borderAdapter.itemClickListener = object : BorderAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId: Int) {
                borderViewModel.clickItem(challengeId)
            }
        }
        borderViewModel.borders.observe(this, Observer {
            (binding.rvBorder.adapter as BorderAdapter).setData(it)
            binding.tvBSelectBtn.visibility = borderViewModel.getSelectBtnVisibility()
            binding.tvBReleaseBtn.visibility = borderViewModel.getReleaseBtnVisibility()

        })

//
//
//        db = MyDatabase.getInstance(this)
//        val userDao = db.getUserDAO()
//        val borderDao = db.getBorderDAO()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val userEntity = userDao.getUser()[0]
//            val borderList = borderDao.getAllBorders()
//            binding.tvBMyCoin.text = userEntity.currentCoin.toString()
//            binding.ifbBProfileBorder.backgroundTintList =
//                ColorStateList.valueOf(borderList[userEntity.currentBorder].color)
//
//            val borderOptionList = ArrayList<BorderOption>()
//            borderList.forEach { b ->
//                borderOptionList.add(BorderOption(b.number, b.color, b.name, b.price, false, false))
//            }
//            borderOptionsViewModel.setItems(borderOptionList)
//        }
//
//        binding.cvBSelectBtn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                val userEntity = userDao.getUser()[0]
//                val checkedItem = borderOptionsViewModel.getCheckedItem()
//                if(checkedItem == null)
//                    cancel()
//                userEntity.currentBorder = borderOptionsViewModel.getCheckedItem()!!.ID
//                userDao.updateUser(userEntity)
//
//                val changeCurrentBorderResponse = retrofit.changeCurrentBorder(ChangeCurrentBorderRequest(userEntity.name, userEntity.currentBorder))
//                if(changeCurrentBorderResponse.isSuccessful) {
//                    val data = changeCurrentBorderResponse.body()!!
//                    Log.d("retrofit_border_changeBorder", data.success.toString())
//                } else {
//                    Log.d("retrofit_border_changeBorder", changeCurrentBorderResponse.message().toString())
//                }
//            }
//        }
//
//        binding.cvBReleaseBtn.setOnClickListener {
//
//            CoroutineScope(Dispatchers.IO).launch {
//                val userEntity = userDao.getUser()[0]
//                val checkedItem = borderOptionsViewModel.getCheckedItem()
//                if (checkedItem == null)
//                    cancel()
//
//                val buyBorderResponse =
//                    retrofit.buyBorder(BuyBorderRequest(userEntity.name, checkedItem!!.ID))
//                if (buyBorderResponse.isSuccessful) {
//                    val data = buyBorderResponse.body()!!
//                    Log.d("retrofit_border_buy", data.success.toString())
//                    userEntity.currentCoin -= checkedItem.price
//                    userEntity.ownBorders.add(checkedItem.ID)
//                    userDao.updateUser(userEntity)
//                    borderOptionsViewModel.unlockItem(checkedItem.ID)
//                    val setMemberCoinResponse = retrofit.setMemberCoins(
//                        SetMemberCoinsRequest(
//                            userEntity.name,
//                            userEntity.currentCoin
//                        )
//                    )
//                    if (setMemberCoinResponse.isSuccessful) {
//                        val data = setMemberCoinResponse.body()!!
//                        Log.d("retrofit_border_setCoin", data.success.toString())
//                    } else {
//                        Log.d("retrofit_border_setCoin", buyBorderResponse.message().toString())
//                    }
//                } else {
//                    Log.d("retrofit_border_buy", buyBorderResponse.message().toString())
//                }
//            }
//        }

        binding.cvBBackBtn.setOnClickListener {
            finish()
        }
    }
}


