package com.example.klab2challenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemHomeChallengeBinding
import com.example.klab2challenge.retrofit.GetChallengeResponse

class ChallengeAdapter(var items : List<GetChallengeResponse>) : RecyclerView.Adapter<ChallengeAdapter.ViewHolder>() {

    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    inner class ViewHolder(val binding: ItemHomeChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GetChallengeResponse) {
            binding.root.setOnClickListener {
                itemClickListener!!.onItemClicked(item.challengeId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(list:List<GetChallengeResponse>) {
        items = list
        notifyDataSetChanged()
    }
}