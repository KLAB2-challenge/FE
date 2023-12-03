package com.example.klab2challenge.ui.record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.klab2challenge.databinding.ActivityRecordListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordListActivity : AppCompatActivity() {
    lateinit var _binding : ActivityRecordListBinding
    private val binding : ActivityRecordListBinding get() = _binding
    private val recordListViewModel : RecordListViewModel by viewModel()
    private var challengeId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecordListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        challengeId = intent.getIntExtra("challengeId", -1)

        binding.rvRl.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = RecordListAdapter(applicationContext)
        adapter.setOnItemClickListener(object : RecordListAdapter.OnItemClickListener {
            override fun onItemClicked(recordId: Int) {
                val i = Intent(applicationContext, RecordDetailActivity::class.java)
                i.putExtra("recordId", recordId)
                i.putExtra("challengeId", challengeId)
                startActivity(i)
            }
        })
        binding.rvRl.adapter = adapter
        recordListViewModel.records.observe(this, Observer {
            (binding.rvRl.adapter as RecordListAdapter).setData(it)
        })


        binding.cvRlBackBtn.setOnClickListener {
            finish()
        }


        binding.cvRlPostBtn.setOnClickListener {
            val i = Intent(applicationContext, AddRecordActivity::class.java)
            i.putExtra("challengeId", challengeId)
            startActivity(i)
        }

        recordListViewModel.requestRecords(challengeId)
    }

}