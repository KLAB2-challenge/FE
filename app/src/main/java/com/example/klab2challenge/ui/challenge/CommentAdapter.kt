package com.example.klab2challenge.ui.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemRecordDetailCommentBinding
import com.example.klab2challenge.retrofit.GetCommentResponse

class CommentAdapter(var items: List<GetCommentResponse>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

    inner class ViewHolder(val binding : ItemRecordDetailCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GetCommentResponse) {
            binding.tvRdCommentName.text = item.memberName
            binding.tvRdCommentContent.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecordDetailCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list : List<GetCommentResponse>) {
        items = list
        notifyDataSetChanged()
    }


}