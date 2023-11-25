package com.example.klab2challenge.ui.challenge

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityNewChallengeBinding

class NewChallengeActivity : AppCompatActivity() {
    lateinit var binding : ActivityNewChallengeBinding
    val items = resources.getStringArray(R.array.freq_array)
    //어댑터 연결 다시 잘 해보자...
    val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
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
        binding = ActivityNewChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvNcBackBtn.setOnClickListener {
            finish()
        }

        binding.tvNcAddimage.setOnClickListener {
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

        binding.ivNcAddimage.setOnClickListener {
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
