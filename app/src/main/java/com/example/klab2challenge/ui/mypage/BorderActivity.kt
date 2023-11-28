package com.example.klab2challenge.ui.mypage

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.klab2challenge.databinding.ActivityBorderBinding
import com.example.klab2challenge.db.ChallengeDatabase
import com.example.klab2challenge.retrofit.BuyBorderRequest
import com.example.klab2challenge.retrofit.ChangeCurrentBorderRequest
import com.example.klab2challenge.retrofit.RetrofitInterface
import com.example.klab2challenge.retrofit.SetMemberCoinsRequest
import com.example.klab2challenge.ui.mychallenge.BorderAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BorderActivity : AppCompatActivity() {
    lateinit var binding: ActivityBorderBinding

    private lateinit var db: ChallengeDatabase
    private lateinit var retrofit: RetrofitInterface

    private var borderOptionsViewModel = BorderOptionsViewModel()

    lateinit var color: ArrayList<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rvBorder.layoutManager = GridLayoutManager(this, 2)
        val borderAdapter = BorderAdapter(borderOptionsViewModel.itemList.value!!)
        borderAdapter.itemClickListener = object : BorderAdapter.OnItemClickListener {
            override fun onItemClicked(challengeId: Int) {
                borderOptionsViewModel.clickItem(challengeId)
            }
        }
        borderOptionsViewModel.itemList.observe(this, Observer {
            (binding.rvBorder.adapter as BorderAdapter).setData(it)
            binding.tvBSelectBtn.visibility = borderOptionsViewModel.getSelectBtnVisibility()
            binding.tvBReleaseBtn.visibility = borderOptionsViewModel.getReleaseBtnVisibility()
        })



        db = ChallengeDatabase.getInstance(this)
        val userDao = db.getUserDAO()
        val borderDao = db.getBorderDAO()

        CoroutineScope(Dispatchers.IO).launch {
            val userEntity = userDao.getUser()[0]
            val borderList = borderDao.getAllBorders()
            binding.tvBMyCoin.text = userEntity.currentCoin.toString()
            binding.ifbBProfileBorder.backgroundTintList =
                ColorStateList.valueOf(borderList[userEntity.currentBorder].color)

            val borderOptionList = ArrayList<BorderOption>()
            borderList.forEach { b ->
                borderOptionList.add(BorderOption(b.number, b.color, b.name, b.price, false, false))
            }
            borderOptionsViewModel.setItems(borderOptionList)
        }

        binding.cvBSelectBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val userEntity = userDao.getUser()[0]
                val checkedItem = borderOptionsViewModel.getCheckedItem()
                if(checkedItem == null)
                    cancel()
                userEntity.currentBorder = borderOptionsViewModel.getCheckedItem()!!.ID
                userDao.updateUser(userEntity)

                val changeCurrentBorderResponse = retrofit.changeCurrentBorder(ChangeCurrentBorderRequest(userEntity.userName, userEntity.currentBorder))
                if(changeCurrentBorderResponse.isSuccessful) {
                    val data = changeCurrentBorderResponse.body()!!
                    Log.d("retrofit_border_changeBorder", data.success.toString())
                } else {
                    Log.d("retrofit_border_changeBorder", changeCurrentBorderResponse.message().toString())
                }
            }
        }

        binding.cvBReleaseBtn.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val userEntity = userDao.getUser()[0]
                val checkedItem = borderOptionsViewModel.getCheckedItem()
                if (checkedItem == null)
                    cancel()

                val buyBorderResponse =
                    retrofit.buyBorder(BuyBorderRequest(userEntity.userName, checkedItem!!.ID))
                if (buyBorderResponse.isSuccessful) {
                    val data = buyBorderResponse.body()!!
                    Log.d("retrofit_border_buy", data.success.toString())
                    userEntity.currentCoin -= checkedItem.price
                    userEntity.ownBorders.add(checkedItem.ID)
                    userDao.updateUser(userEntity)
                    borderOptionsViewModel.unlockItem(checkedItem.ID)
                    val setMemberCoinResponse = retrofit.setMemberCoins(
                        SetMemberCoinsRequest(
                            userEntity.userName,
                            userEntity.currentCoin
                        )
                    )
                    if (setMemberCoinResponse.isSuccessful) {
                        val data = setMemberCoinResponse.body()!!
                        Log.d("retrofit_border_setCoin", data.success.toString())
                    } else {
                        Log.d("retrofit_border_setCoin", buyBorderResponse.message().toString())
                    }
                } else {
                    Log.d("retrofit_border_buy", buyBorderResponse.message().toString())
                }
            }
        }

        binding.cvBBackBtn.setOnClickListener {
            finish()
        }
    }
}


