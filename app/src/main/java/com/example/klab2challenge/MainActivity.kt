package com.example.klab2challenge

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.annotation.GlideModule
import com.example.klab2challenge.databinding.ActivityMainBinding
import com.example.klab2challenge.retrofit.GetMemberInfosRequest
import com.example.klab2challenge.retrofit.GetMemberInfosResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.saveUserBorder
import com.example.klab2challenge.retrofit.saveUserCoin
import com.example.klab2challenge.retrofit.saveUserName
import com.example.klab2challenge.retrofit.saveUserProfileUrl
import com.example.klab2challenge.retrofit.saveUserTotalCoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var color : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        color = arrayListOf(
            getColor(R.color.gold),
            getColor(R.color.green),
            getColor(R.color.cherry),
            getColor(R.color.blueberry),
            getColor(R.color.sunny),
            getColor(R.color.rainy)
        )

        saveUserName(this, "user1")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        RetrofitUtil.getRetrofitUtil()
            .getMemberInfos(GetMemberInfosRequest(getUserName(this))).enqueue(object :
                Callback<GetMemberInfosResponse> {
                override fun onResponse(
                    call: Call<GetMemberInfosResponse>,
                    response: Response<GetMemberInfosResponse>
                ) {
                    if(response.isSuccessful) {
                        val data = response.body()!!
                        saveUserName(applicationContext, data.memberName)
                        Log.d("hyunheemp", data.infos.currentBorder.toString())
                        Log.d("hyunheempborder", color.get(data.infos.currentBorder).toString())
                        saveUserBorder(applicationContext, color.get(data.infos.currentBorder))
                        saveUserCoin(applicationContext, data.infos.holdingCoins)
                        saveUserTotalCoin(applicationContext, data.infos.totalCoins)
                        saveUserProfileUrl(applicationContext, data.infos.imageUrl)
                    } else {
                        Log.d("hyunheemp", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<GetMemberInfosResponse>, t: Throwable) {
                    Log.d("hyunheemp", t.message.toString())
                }

            })
    }
}