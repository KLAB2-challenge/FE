package com.example.klab2challenge.ui.record

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ItemRecordDetailCommentBinding
import com.example.klab2challenge.db.entity.BorderEntity
import com.example.klab2challenge.db.entity.CommentEntity
import com.example.klab2challenge.retrofit.GetCommentResponse

class CommentAdapter(var context: Context) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var items = arrayListOf<CommentEntity>()
    var borderList = arrayListOf<BorderEntity>()


    inner class ViewHolder(val binding: ItemRecordDetailCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentEntity) {
            binding.tvRdCommentName.text = item.userName
            binding.tvRdCommentContent.text = item.content
            binding.tvRdCommentDate.text = item.date
            binding.cvRdCommentImgBorder.backgroundTintList = ColorStateList.valueOf(borderList[item.userCurrentBorder].color)
            Glide.with(context).load(item.userImage).into(binding.ivRdCommentImg)
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

    fun setData(list: List<CommentEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    fun setBorder(list:List<BorderEntity>) {
        borderList.clear()
        borderList.addAll(list)
        notifyDataSetChanged()
    }
}