package com.example.klab2challenge.ui.ranking

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klab2challenge.databinding.ItemRankingListBinding
import com.example.klab2challenge.db.BorderEntity
import com.example.klab2challenge.db.RankingEntity

class RankingAdapter(var context: Context) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    var items = arrayListOf<RankingEntity>()
    var borderList = arrayListOf<BorderEntity>()
    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    inner class ViewHolder(val binding: ItemRankingListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RankingEntity) {
//            binding.root.setOnClickListener {
//                itemClickListener!!.onItemClicked(item.challengeId)
//            }
            binding.tvRankingMyRank.text = "# " + (item.ranking + 1).toString()
            binding.tvRankingCoin.text = item.totalCoin.toString()
            binding.tvRankingProfileName.text = item.userName
            binding.cvRankingProfileImgBorder.backgroundTintList = ColorStateList.valueOf(borderList[item.currentBorder].color)
            Glide.with(context).load(item.image).into(binding.ivRankingProfileImg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRankingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(list:List<RankingEntity>) {
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