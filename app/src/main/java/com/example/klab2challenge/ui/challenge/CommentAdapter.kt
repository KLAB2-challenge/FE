package com.example.klab2challenge.ui.challenge

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ItemRecordDetailCommentBinding
import com.example.klab2challenge.retrofit.GetCommentResponse

class CommentAdapter(var context: Context, var items: List<GetCommentResponse>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {


    val color = arrayListOf(
        ContextCompat.getColor(context, R.color.gold),
        ContextCompat.getColor(context, R.color.green),
        ContextCompat.getColor(context, R.color.cherry),
        ContextCompat.getColor(context, R.color.blueberry),
        ContextCompat.getColor(context, R.color.sunny),
        ContextCompat.getColor(context, R.color.rainy)
    )

    inner class ViewHolder(val binding: ItemRecordDetailCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetCommentResponse) {
            binding.tvRdCommentName.text = item.memberName
            binding.tvRdCommentContent.text = item.content
            binding.tvRdCommentDate.text = item.infos.date
            binding.cvRdCommentImgBorder.backgroundTintList = ColorStateList.valueOf(color.get(item.memberCurrentBorder))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecordDetailCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list: List<GetCommentResponse>) {
        items = list
        notifyDataSetChanged()
    }
}