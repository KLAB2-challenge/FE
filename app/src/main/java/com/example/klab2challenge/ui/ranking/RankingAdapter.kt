package com.example.klab2challenge.ui.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemHomeChallengeBinding
import com.example.klab2challenge.databinding.ItemRankingListBinding
import com.example.klab2challenge.retrofit.GetChallengeResponse
import com.example.klab2challenge.retrofit.Member

class RankingAdapter(var items : List<Member>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    inner class ViewHolder(val binding: ItemRankingListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Member, position: Int) {
//            binding.root.setOnClickListener {
//                itemClickListener!!.onItemClicked(item.challengeId)
//            }
            binding.tvRankingMyRank.text = "# " + (position + 4).toString()
            binding.tvRankingCoin.text = item.infos.holdingCoins.toString()
            binding.tvRankingProfileName.text = item.name
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