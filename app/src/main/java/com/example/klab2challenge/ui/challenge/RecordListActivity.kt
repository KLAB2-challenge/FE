package com.example.klab2challenge.ui.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.ActivityRecordListBinding

class RecordListActivity : AppCompatActivity() {
    lateinit var _binding : ActivityRecordListBinding
    private val binding : ActivityRecordListBinding get() = _binding
    private val recordViewModel = RecordViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecordListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRl.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = RecordListAdapter(recordViewModel.itemList.value!!)
        adapter.setOnItemClickListener(object : RecordListAdapter.OnItemClickListener {
            override fun onItemClicked() {
                val i = Intent(applicationContext, RecordDetailActivity::class.java)
                startActivity(i)
            }
        })
        binding.rvRl.adapter = adapter
        recordViewModel.itemList.observe(this, Observer {
            (binding.rvRl.adapter as RecordListAdapter).setData(it)
        })

        binding.cvRlBackBtn.setOnClickListener {
            finish()
        }
        binding.cvRlPostBtn.setOnClickListener {
            val i = Intent(applicationContext, PostRecordActivity::class.java)
            startActivity(i)
        }
    }
}