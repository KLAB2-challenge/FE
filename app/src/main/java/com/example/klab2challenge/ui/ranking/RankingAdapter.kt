package com.example.klab2challenge.ui.ranking

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.R
import com.example.klab2challenge.databinding.ItemHomeChallengeBinding
import com.example.klab2challenge.databinding.ItemRankingListBinding
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.Member

class RankingAdapter(var context: Context, var items : List<Member>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    val color = arrayListOf(
    getColor(context, R.color.gold), getColor(context, R.color.green), getColor(context, R.color.cherry),
    getColor(context, R.color.blueberry), getColor(context, R.color.sunny), getColor(context, R.color.rainy)
    )

    inner class ViewHolder(val binding: ItemRankingListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Member, position: Int) {
//            binding.root.setOnClickListener {
//                itemClickListener!!.onItemClicked(item.challengeId)
//            }
            binding.tvRankingMyRank.text = "# " + (position + 4).toString()
            binding.tvRankingCoin.text = item.infos.holdingCoins.toString()
            binding.tvRankingProfileName.text = item.name
            Log.d("hyunheerank", item.infos.currentBorder.toString())
            binding.cvRankingProfileImgBorder.backgroundTintList = ColorStateList.valueOf(getColor(context, color.get(item.infos.currentBorder)))
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
        holder.bind(items[position], position)
    }

    fun setData(list:List<Member>) {
        items = list
        notifyDataSetChanged()
    }
}