package com.example.klab2challenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemHomeChallengeBinding

class RelatedChallengeAdapter(var items : List<String>) : RecyclerView.Adapter<RelatedChallengeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : ItemHomeChallengeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {

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

    fun setData(list: List<String>){
        items = list
        notifyDataSetChanged()
    }
}