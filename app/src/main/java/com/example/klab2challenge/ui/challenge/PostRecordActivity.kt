package com.example.klab2challenge.ui.challenge

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.klab2challenge.databinding.ActivityPostRecordBinding
import com.example.klab2challenge.retrofit.ProofPostContents
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.SetProofPostRequest
import com.example.klab2challenge.retrofit.SetProofPostResponse
import com.example.klab2challenge.retrofit.getUserName
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PostRecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityPostRecordBinding
    private var challengeId = -1

    // 갤러리 open
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }

    // 가져온 사진 보여주기
    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val uri = data?.data
                binding.ivPrAddedimage.setImageURI(uri)
                binding.ivPrAddedimage.visibility = View.VISIBLE
                Log.d("hyunhee", uri.toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        challengeId = intent.getIntExtra("challengeId", -1)

        binding.cvPrPostBtn.setOnClickListener {
            val postRecord = SetProofPostRequest(
                challengeId,
                getUserName(this),
                ProofPostContents(
                    binding.etPrTitleInput.text.toString(),
                    binding.etPrContentInput.text.toString(),
                    ""
                )
            )

//            var j = Gson().toJson(postRecord)
//            val jsonObject = JSONObject(j)
//            val mediaType = "application/json; charset=utf-8".toMediaType()
//            val body = jsonObject.toString().toRequestBody(mediaType)

            RetrofitUtil.getRetrofitUtil().setProofPost(postRecord)
                .enqueue(object : Callback<SetProofPostResponse> {
                    override fun onResponse(
                        call: Call<SetProofPostResponse>,
                        response: Response<SetProofPostResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("seohyun", "post success!!")
                            finish()
                            val i = Intent(applicationContext, RecordListActivity::class.java)
                            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            i.putExtra("challengeId", challengeId)
                            startActivity(i)
                        } else {
                            Log.d("seohyun", response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<SetProofPostResponse>, t: Throwable) {
                        Log.d("seohyun", t.message.toString())
                    }

                })
        }

        binding.cvPrBackBtn.setOnClickListener {
            finish()
        }

        binding.tvPrAddimage.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            } else {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }

        binding.ivPrAddimage.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_MEDIA_IMAGES
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            } else {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }
}

