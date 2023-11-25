package com.example.klab2challenge.ui.challenge

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityAddChallengeBinding
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.RetrofitUtil
import com.example.klab2challenge.retrofit.SetChallengeRequest
import com.example.klab2challenge.retrofit.SetChallengeResponse
import com.example.klab2challenge.retrofit.getUserName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddChallengeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddChallengeBinding
    lateinit var items: Array<String>

    //어댑터 연결 다시 잘 해보자...
    lateinit var myAdapter: ArrayAdapter<String>
    //api연결은 아직

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
                binding.ivNcAddedimage.setImageURI(uri)
                binding.ivNcAddedimage.visibility = View.VISIBLE
                Log.d("hyunhee", uri.toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items = resources.getStringArray(R.array.freq_array)
        myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        binding.spNcFreqInput.adapter = myAdapter

        binding.cvNcCreateBtn.setOnClickListener {
            RetrofitUtil.getRetrofitUtil()
                .setChallenge(
                    SetChallengeRequest(
                        getUserName(this), ChallengeContents(
                            binding.etNcTitleInput.text.toString(), binding.etNcContentInput.text.toString(), ""
                        ), ChallengeInfos(
                            binding.etNcStartInput.editableText.toString(), binding.etNcFinishInput.editableText.toString(), binding.spNcFreqInput.selectedItem.toString(), 0, false
                        )
                    )
                ).enqueue(object : Callback<SetChallengeResponse> {
                    override fun onResponse(
                        call: Call<SetChallengeResponse>,
                        response: Response<SetChallengeResponse>
                    ) {
                        if(response.isSuccessful) {
                            Log.d("hyunhee", response.body().toString())
                        }else {
                            Log.d("hyunhee", response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<SetChallengeResponse>, t: Throwable) {
                        Log.d("hyunhee", t.message!!)
                    }

                })
            finish()
        }

        binding.cvNcBackBtn.setOnClickListener {
            finish()
        }

        binding.tvNcAddimage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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

        binding.ivNcAddimage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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
