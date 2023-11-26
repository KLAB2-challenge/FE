package com.example.klab2challenge.ui.mypage

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityBorderBinding
import com.example.klab2challenge.retrofit.BuyBorderRequest
import com.example.klab2challenge.retrofit.BuyBorderResponse
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.GetMemberAllBordersRequest
import com.example.klab2challenge.retrofit.GetMemberAllBordersResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.getUserBorder
import com.example.klab2challenge.retrofit.getUserCoin
import com.example.klab2challenge.retrofit.getUserName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class BorderActivity : AppCompatActivity() {
    lateinit var binding: ActivityBorderBinding

    private lateinit var border: ArrayList<View>
    private lateinit var icCheck: ArrayList<View>
    private lateinit var icLock: ArrayList<View>
    private var borderCoin = arrayListOf(10,20,30,40,50,60)
    private var isChecked = arrayListOf(false, false, false, false, false, false)
    private var isUnlocked = arrayListOf(false, false, false, false, false, false)
    lateinit var color: ArrayList<Int>
    var myCoin by Delegates.notNull<Int>()
    private val borderList : ArrayList<Int> //수정필요 머..멀라..어떤 그거 일단 냅둬


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCoin = getUserCoin(this).toString().toInt()
        binding.tvBMyCoin.text = myCoin.toString()
        binding.ifbBProfileBorder.backgroundTintList = ColorStateList.valueOf(getUserBorder(this))

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

        icLock = arrayListOf(
            binding.ivBGoldLock, binding.ivBGreenLock, binding.ivBCherryLock,
            binding.ivBBlueberryLock, binding.ivBSunnyLock, binding.ivBRainyLock
        )


        //border 이거 어떻게.....
        RetrofitUtil.getRetrofitUtil()
            .getMemberAllBorders(GetMemberAllBordersRequest(getUserName(this)))
            .enqueue(object : Callback<GetMemberAllBordersResponse> {
                override fun onResponse(
                    call: Call<GetMemberAllBordersResponse>,
                    response: Response<GetMemberAllBordersResponse>
                ) {
                    if (response.isSuccessful) {
                        val list = ArrayList<GetChallengeResponse>()
                        //여기서 어떻게 구현? 리스트 받아와서 해당 되는 친구들은 자물쇠 사라지게
//                        for(i in 0..5 step(1)){
//                            if ( list 안에 해당 부분 있으면) {
//                                isUnlocked[i]=true
//                                icLock[i].visibility = View.GONE
//                            }
//                        }
                    } else {
                        Log.d("chaerinBorder", response.errorBody().toString())
                    }
                }
                override fun onFailure(call: Call<GetMemberAllBordersResponse>, t: Throwable) {
                    Log.d("chaerinBorder", t.message.toString())
                }
            })



        binding.cvBBackBtn.setOnClickListener {
            finish()
        }

        initClickListener() //setOnClickListener 추가



    }

    private fun initClickListener() {
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

            //btn변경 : 활성화
            binding.cvBBtn.strokeColor = getColor(R.color.BK)
            binding.tvBBtn.text = "Release"
            binding.tvBBtn.setTextColor(getColor(R.color.BK))
            updateBtn(true, i+1)

            //내 코인수와 비교했을 때 구매 불가능이면 빨간색 띄우기
            var needCoin = myCoin - borderCoin[i] //10에 내 코인 넣기
            if (needCoin < 0){
                binding.tvBWarningLabel.visibility = View.VISIBLE
                binding.tvBNoticeLabel2.visibility = View.VISIBLE
                binding.tvBWarningCoin.text = needCoin.toString()
                binding.tvBWarningCoin.visibility = View.VISIBLE

                //btn변경 : 비활성화
                binding.cvBBtn.strokeColor = getColor(R.color.GS_60)
                binding.tvBBtn.text = "Select"
                binding.tvBBtn.setTextColor(getColor(R.color.GS_60))

                updateBtn(false, i+1)
            }
        }
        else {
            binding.tvBNoticeLabel.visibility = View.GONE
            binding.tvBNoticeCoin.visibility = View.GONE
            binding.tvBNoticeLabel2.visibility = View.GONE

            binding.tvBWarningLabel.visibility = View.GONE
            binding.tvBNoticeLabel2.visibility = View.GONE
            binding.tvBWarningCoin.visibility = View.GONE

            //btn변경 : 활성화
            binding.cvBBtn.strokeColor = getColor(R.color.BK)
            binding.tvBBtn.text = "Select"
            binding.tvBBtn.setTextColor(getColor(R.color.BK))

            updateBtn(true, i+1)
        }
    }

    private fun borderChecking(i: Int) {
        if (!isChecked[i]) {
            for (i in 0..5 step(1)){
                icCheck[i].visibility = View.GONE
                isChecked[i] = false
            }

            icCheck[i].visibility = View.VISIBLE
            binding.ifbBProfileBorder.setBackgroundColor(color[i])
            isChecked[i] = true

        } else {
            icCheck[i].visibility = View.GONE
            binding.ifbBProfileBorder.setBackgroundColor(getColor(R.color.WH))
            isChecked[i] = false

            //btn변경 : 비활성화
            binding.cvBBtn.strokeColor = getColor(R.color.GS_60)
            binding.tvBBtn.text = "Select"
            binding.tvBBtn.setTextColor(getColor(R.color.GS_60))

            updateBtn(false, i+1)
        }
    }

    private fun updateBtn(res: Boolean, i: Int) {
        if (res) {
            binding.cvBBtn.setOnClickListener {
                //구매과정 진행
                RetrofitUtil.getRetrofitUtil()
                    .buyBorder(BuyBorderRequest(getUserName(this).toString(), i)
                    .enqueue(object : Callback<BuyBorderResponse>{
                        override fun onResponse(
                            call: Call<BuyBorderResponse>,
                            response: Response<BuyBorderResponse>
                        ) {
                            Log.d("chaerinBorder", response.errorBody().toString())
                        }

                        override fun onFailure(call: Call<BuyBorderResponse>, t: Throwable) {
                            Log.d("chaerinBorder", t.message.toString())
                        }
                    })
            }
        }
        else {
            //버튼 비활성화
            binding.cvBBtn.setOnClickListener {  }
        }
    }
}


