package com.example.klab2challenge.ui.challenge

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
import com.example.klab2challenge.retrofit.ChallengeContents
import com.example.klab2challenge.retrofit.ChallengeInfos
import com.example.klab2challenge.retrofit.SetChallengeRequest
import com.example.klab2challenge.retrofit.SetChallengeResponse
import com.example.klab2challenge.retrofit.getUserName
import com.example.klab2challenge.retrofit.getUserProfileUrl
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


class AddChallengeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddChallengeBinding

    private val addChallengeActivityViewModel: AddChallengeActivityViewModel by viewModel()
    lateinit var items: Array<String>

    lateinit var myAdapter: ArrayAdapter<String>

    var fileToUpload: MultipartBody.Part? = null

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
                binding.ivNcAddedimage.setImageURI(imageUrl);
                binding.ivNcAddedimage.visibility = View.VISIBLE

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items = resources.getStringArray(R.array.freq_array)
        myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.spNcFreqInput.adapter = myAdapter

        addChallengeActivityViewModel.users.observe(this, Observer {

        })

        binding.cvNcCreateBtn.setOnClickListener {
            Log.d("addChallenge", "1")
            val userInfo = addChallengeActivityViewModel.users.value!!.get(0)
            Log.d("addChallenge", "2")
            runBlocking {
                async {
                addChallengeActivityViewModel.requestSetChallenge(
                    fileToUpload, SetChallengeRequest(
                        userInfo.name, ChallengeContents(
                            binding.etNcTitleInput.text.toString(),
                            binding.etNcContentInput.text.toString(),
                            userInfo.image
                        ), ChallengeInfos(
                            binding.etNcStartInput.text.toString(),
                            binding.etNcFinishInput.text.toString(),
                            binding.spNcFreqInput.selectedItem.toString(),
                            0,
                            false
                        )
                    )
                )}.await()
                finish()
            }

        }

        binding.cvNcBackBtn.setOnClickListener {
            finish()
        }

        binding.tvNcAddimage.setOnClickListener {
            addImage()
        }

        binding.ivNcAddimage.setOnClickListener {
            addImage()
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
