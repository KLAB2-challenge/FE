package com.example.klab2challenge.ui.record

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ItemRecordListBinding
import com.example.klab2challenge.db.entity.RecordEntity
import com.example.klab2challenge.retrofit.GetProofPostResponse

class RecordListAdapter(val context: Context) : RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {
    var items = arrayListOf<RecordEntity>()
    interface OnItemClickListener {
        fun onItemClicked(recordId: Int)
    }

    private var onItemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    val color = arrayListOf(
        ContextCompat.getColor(context, R.color.gold),
        ContextCompat.getColor(context, R.color.green),
        ContextCompat.getColor(context, R.color.cherry),
        ContextCompat.getColor(context, R.color.blueberry),
        ContextCompat.getColor(context, R.color.sunny),
        ContextCompat.getColor(context, R.color.rainy)
    )

    inner class ViewHolder(val binding : ItemRecordListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RecordEntity) {
            binding.root.setOnClickListener {
                onItemClickListener!!.onItemClicked(item.number)
            }
            binding.tvItemRlTitle.text = item.title
            binding.tvItemRlContent.text = item.description
            binding.tvRdUserName.text = item.userName
            binding.tvRdPostDate.text = item.date
            binding.tvItemRlMessage.text = item.commentNum.toString()
            binding.cvPrUserImgBorder.backgroundTintList = ColorStateList.valueOf(color.get(item.userCurrentBorder))
            Glide.with(context).load(item.userImage).into(binding.ivPrUserImg)
            Glide.with(context).load(item.image).into(binding.ivItemRl)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(list: List<RecordEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}