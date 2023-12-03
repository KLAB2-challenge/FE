package com.example.klab2challenge.ui.mychallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemMyChallengeBinding
import com.example.klab2challenge.db.entity.ChallengeEntity

class MCPAdapter() : RecyclerView.Adapter<MCPAdapter.ViewHolder>() {
    var items = arrayListOf<ChallengeEntity>()
    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }

    inner class ViewHolder(val binding: ItemMyChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ChallengeEntity) {
            binding.root.setOnClickListener {
                itemClickListener!!.onItemClicked(item.number)
            }
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

    fun setData(list:List<ChallengeEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}