package com.example.klab2challenge.ui.mychallenge

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemBorderBinding
import com.example.klab2challenge.db.BorderEntity
import com.example.klab2challenge.ui.mypage.BorderOption

class BorderAdapter(var items : List<BorderOption>) : RecyclerView.Adapter<BorderAdapter.ViewHolder>() {

    var itemClickListener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClicked(challengeId: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    inner class ViewHolder(val binding: ItemBorderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BorderOption) {
            binding.root.setOnClickListener {
                itemClickListener!!.onItemClicked(item.ID)
            }
            binding.tvItemText.text = item.name
            binding.cvItemBorder.backgroundTintList = ColorStateList.valueOf(item.color)
            binding.ivItemCheck.visibility = if(item.isChecked) View.VISIBLE else View.GONE
            binding.ivItemLock.visibility = if(item.isLocked) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBorderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(list:List<BorderOption>) {
        items = list
        notifyDataSetChanged()
    }
}