package com.example.klab2challenge.ui.ranking

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ItemRankingListBinding
import com.example.klab2challenge.db.BorderEntity
import com.example.klab2challenge.db.RankingEntity

class RankingAdapter(var context: Context, var items : List<RankingEntity>, var borderList : List<BorderEntity>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

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
            binding.tvRankingMyRank.text = "# " + (item.ranking + 4).toString()
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
        items = list
        notifyDataSetChanged()
    }
}