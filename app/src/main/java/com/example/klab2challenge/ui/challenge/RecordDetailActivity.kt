package com.example.klab2challenge.ui.challenge

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityRecordDetailBinding
import com.example.klab2challenge.retrofit.GetAllCommentsResponse
import com.example.klab2challenge.retrofit.GetProofPostResponse
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.SetCommentRequest
import com.example.klab2challenge.retrofit.SetCommentResponse
import com.example.klab2challenge.retrofit.SetMemberCoinsRequest
import com.example.klab2challenge.retrofit.SetMemberCoinsResponse
import com.example.klab2challenge.retrofit.getUserBorder
import com.example.klab2challenge.retrofit.getUserCoin
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.getUserTotalCoin
import com.example.klab2challenge.retrofit.saveUserCoin
import com.example.klab2challenge.retrofit.saveUserTotalCoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordDetailActivity : AppCompatActivity() {
    lateinit var _binding: ActivityRecordDetailBinding
    private val commentViewModel = CommentViewModel()
    private val recordDetailViewModel = RecordDetailViewModel()
    private var recordId = -1

    lateinit var color: ArrayList<Int>

    val binding: ActivityRecordDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recordId = intent.getIntExtra("recordId", -1)


        Log.d("hyunheeRDborder", getUserBorder(this).toString())
        binding.cvRdUserImgBorder.backgroundTintList = ColorStateList.valueOf(getUserBorder(this))

        binding.rvRdComments.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRdComments.adapter = CommentAdapter(this, commentViewModel.commentList.value!!)
        commentViewModel.commentList.observe(this, Observer {
            (binding.rvRdComments.adapter as CommentAdapter).setData(it)
            if (it.size == 0) {
                binding.vRdBottomLine.visibility = View.GONE
            } else {
                binding.vRdBottomLine.visibility = View.VISIBLE
            }
        })

        binding.cvRdBackBtn.setOnClickListener {
            finish()
        }

        recordDetailViewModel.recordDetail.observe(this, Observer {
            binding.tvRdRecordTitle.text = it.contents.title
            binding.tvRdRecordContent.text = it.contents.content
            binding.tvRdUserName.text = it.memberName
            binding.tvRdPostDate.text = it.infos.date
        })

        binding.ivRdSend.setOnClickListener {
            val commentRequest =
                SetCommentRequest(getUserName(this), binding.etRdComment.text.toString(), recordId)
            RetrofitUtil.getRetrofitUtil().setComment(commentRequest)
                .enqueue(object : Callback<SetCommentResponse> {
                    override fun onResponse(
                        call: Call<SetCommentResponse>,
                        response: Response<SetCommentResponse>
                    ) {
                        if (response.isSuccessful) {
                            getComments()
                            earnCoin()
                        } else {
                            Log.d("hyunhee", response.errorBody()!!.toString())
                        }
                    }

                    override fun onFailure(call: Call<SetCommentResponse>, t: Throwable) {
                        Log.d("hyunhee", t.message.toString())
                    }

                })

            binding.etRdComment.text.clear()
        }

        RetrofitUtil.getRetrofitUtil().getProofPost(recordId)
            .enqueue(object : Callback<GetProofPostResponse> {
                override fun onResponse(
                    call: Call<GetProofPostResponse>,
                    response: Response<GetProofPostResponse>
                ) {
                    if (response.isSuccessful) {
                        recordDetailViewModel.setRecordDetail(response.body()!!)
                        Glide.with(this@RecordDetailActivity).load(response.body()!!.memberImageUrl).into(binding.ivRdUserImg)
                        Glide.with(this@RecordDetailActivity).load(response.body()!!.contents.image).into(binding.ivRdRecordImg)
                    } else {
                        Log.d("seohyun", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<GetProofPostResponse>, t: Throwable) {
                    Log.d("seohyun", t.message.toString())
                }
            })

        getComments()
    }

    fun getComments() {
        RetrofitUtil.getRetrofitUtil().getAllComments(recordId)
            .enqueue(object : Callback<GetAllCommentsResponse> {
                override fun onResponse(
                    call: Call<GetAllCommentsResponse>,
                    response: Response<GetAllCommentsResponse>
                ) {
                    if (response.isSuccessful) {
                        commentViewModel.setComments(response.body()!!.getCommentResponses)
                    } else {
                        Log.d("seohyun", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<GetAllCommentsResponse>, t: Throwable) {
                    Log.d("seohyun", t.message.toString())
                }
            })
    }

    fun earnCoin() {
        RetrofitUtil.getRetrofitUtil().setMemberCoins(SetMemberCoinsRequest(getUserName(this), 10))
            .enqueue(object : Callback<SetMemberCoinsResponse> {
                override fun onResponse(
                    call: Call<SetMemberCoinsResponse>,
                    response: Response<SetMemberCoinsResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.d("hyunheeRD", response.body().toString())
                        saveUserCoin(applicationContext, getUserCoin(applicationContext) + 10)
                        saveUserTotalCoin(applicationContext, getUserTotalCoin(applicationContext) + 10)
                    } else {
                        Log.d("hyunheeRD", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<SetMemberCoinsResponse>, t: Throwable) {
                    Log.d("hyunheeRD", t.message.toString())
                }

            })
    }
}