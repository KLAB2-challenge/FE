package com.example.klab2challenge.ui.record

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
import androidx.lifecycle.Observer
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityAddChallengeBinding
import com.example.klab2challenge.databinding.ActivityAddRecordBinding
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.ProofPostContents
import com.example.klab2challenge.retrofit.SetChallengeRequest
import com.example.klab2challenge.retrofit.SetChallengeResponse
import com.example.klab2challenge.retrofit.SetProofPostRequest
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.getUserProfileUrl
import com.example.klab2challenge.ui.record.AddRecordViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.Random


class AddRecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddRecordBinding
    private val addRecordViewModel: AddRecordViewModel by viewModel()
    var fileToUpload: MultipartBody.Part? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val challengeId = intent.getIntExtra("challengeId", -1)

        addRecordViewModel.users.observe(this, Observer {
            binding.cvPrPostBtn.setOnClickListener {
                val userInfo = addRecordViewModel.users.value!!.get(0)
                runBlocking {
                    addRecordViewModel.requestSetRecord(
                        fileToUpload, SetProofPostRequest(
                            challengeId,
                            userInfo.name, ProofPostContents(
                                binding.etPrTitleInput.text.toString(),
                                binding.etPrContentInput.text.toString(),
                                ""
                            )
                        )
                    )
                }
                finish()
            }
        })


        binding.cvPrBackBtn.setOnClickListener {
            finish()
        }

        binding.tvPrAddimage.setOnClickListener {
            addImage()
        }

        binding.ivPrAddimage.setOnClickListener {
            addImage()
        }
    }

    // 갤러리 open
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }

    // 가져온 사진 보여주기
    @SuppressLint("Range")
    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                var imageUrl = data?.data
                binding.ivPrAddedimage.setImageURI(imageUrl);
                binding.ivPrAddedimage.visibility = View.VISIBLE

                val cursor = contentResolver.query(
                    Uri.parse(imageUrl.toString()),
                    null,
                    null,
                    null,
                    null
                )!!
                cursor.moveToFirst()
                var mediaPath =
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
                val file = File(mediaPath)
                val requestBody: RequestBody =
                    file.asRequestBody("image/*".toMediaTypeOrNull())
                fileToUpload =
                    MultipartBody.Part.createFormData("image", file.getName(), requestBody)
                Log.d("hyunhee", fileToUpload.toString())
            }
        }

    private fun addImage() {
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


    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }
}
