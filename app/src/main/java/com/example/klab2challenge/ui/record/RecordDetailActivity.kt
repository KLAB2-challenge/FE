package com.example.klab2challenge.ui.record

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ActivityRecordDetailBinding
import com.example.klab2challenge.retrofit.SetCommentRequest
import com.example.klab2challenge.retrofit.getUserName
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordDetailActivity : AppCompatActivity() {
    lateinit var _binding: ActivityRecordDetailBinding
    private val recordDetailViewModel : RecordDetailViewModel by viewModel()
    private var recordId = -1
    private var challengeId = -1

    val binding: ActivityRecordDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecordDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recordId = intent.getIntExtra("recordId", -1)
        challengeId = intent.getIntExtra("challengeId", -1)


        binding.rvRdComments.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRdComments.adapter = CommentAdapter(this)
        recordDetailViewModel.comments.observe(this, Observer {
            (binding.rvRdComments.adapter as CommentAdapter).setData(it)
            if (it.size == 0) {
                binding.vRdBottomLine.visibility = View.GONE
            } else {
                binding.vRdBottomLine.visibility = View.VISIBLE
            }
        })

        recordDetailViewModel.borders.observe(this, Observer {bl ->
            val borderList = bl
            (binding.rvRdComments.adapter as CommentAdapter).setBorder(bl)
            recordDetailViewModel.recordInfo.observe(this, Observer {
                binding.tvRdRecordTitle.text = it.contents.title
                binding.tvRdRecordContent.text = it.contents.content
                binding.tvRdUserName.text = it.memberName
                binding.tvRdPostDate.text = it.infos.date
                binding.cvRdUserImgBorder.backgroundTintList = ColorStateList.valueOf(bl[it.memberCurrentBorder].color)
                Glide.with(this@RecordDetailActivity).load(it.memberImageUrl).into(binding.ivRdUserImg)
                Glide.with(this@RecordDetailActivity).load(it.contents.image).into(binding.ivRdRecordImg)
            })
        })

        recordDetailViewModel.users.observe(this, Observer {
            binding.ivRdSend.setOnClickListener {
                recordDetailViewModel.requestSetComment(challengeId, recordId, binding.etRdComment.text.toString())
                binding.etRdComment.text.clear()
            }
        })


        binding.cvRdBackBtn.setOnClickListener {
            finish()
        }

        recordDetailViewModel.requestRecord(recordId)
        recordDetailViewModel.requestComments(recordId)
    }
}