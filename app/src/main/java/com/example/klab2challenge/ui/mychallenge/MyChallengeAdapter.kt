package com.example.klab2challenge.ui.mychallenge

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemHomeChallengeBinding
import com.example.klab2challenge.databinding.ItemMyChallengeBinding
import com.example.klab2challenge.db.MCPEntity
import com.example.klab2challenge.retrofit.GetChallengeResponse

class MyChallengeAdapter(var items : List<MCPEntity>) : RecyclerView.Adapter<MyChallengeAdapter.ViewHolder>() {

    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    inner class ViewHolder(val binding: ItemMyChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MCPEntity) {
//            binding.root.setOnClickListener {
//                itemClickListener!!.onItemClicked(item.challengeId)
//            }
            binding.tvMcTitle.text = item.title
            binding.tvItemMyChallengePercent.text = (item.progress * 100).toInt().toString() + "%"

            val layoutParams = binding.cvMcProgress.layoutParams
            layoutParams.width = (binding.cvMcBackProgress.layoutParams.width * item.progress).toInt()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(list:List<MCPEntity>) {
        items = list
        notifyDataSetChanged()
    }
}