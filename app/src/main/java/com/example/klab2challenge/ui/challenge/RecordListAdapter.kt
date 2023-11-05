package com.example.klab2challenge.ui.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemRecordListBinding

class RecordListAdapter(var items: List<String>) : RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked()
    }

    private var onItemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class ViewHolder(val binding : ItemRecordListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.root.setOnClickListener {
                onItemClickListener!!.onItemClicked()
            }
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

    fun setData(list: List<String>) {
        items = list
        notifyDataSetChanged()
    }
}