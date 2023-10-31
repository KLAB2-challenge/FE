package com.example.klab2challenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemRecordDetailCommentBinding

class CommentAdapter(var items: List<String>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

    inner class ViewHolder(val binding : ItemRecordDetailCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
//            binding.tvRdCommentName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecordDetailCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list : List<String>) {
        items = list
        notifyDataSetChanged()
    }


}