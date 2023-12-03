package com.example.klab2challenge.ui.mychallenge

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemBorderBinding
import com.example.klab2challenge.db.entity.BorderEntity

class BorderAdapter() : RecyclerView.Adapter<BorderAdapter.ViewHolder>() {
    var items = arrayListOf<BorderEntity>()
    var itemClickListener : OnItemClickListener? = null
    var cItem  = -1
    interface OnItemClickListener {
        fun onItemClicked(checkedItem: Int)
    }
    inner class ViewHolder(val binding: ItemBorderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BorderEntity, position: Int) {
            binding.clItemBorderContainer.setOnClickListener {
                itemClickListener!!.onItemClicked(item.number)
            }
            binding.cvItemBorderProfile.setOnClickListener {
                itemClickListener!!.onItemClicked(item.number)
            }
            binding.ifbItemBorderProfile.setOnClickListener {
                itemClickListener!!.onItemClicked(item.number)
            }
            binding.cvItemBorder.setOnClickListener {
                itemClickListener!!.onItemClicked(item.number)
            }
            binding.ifbItemBorder.setOnClickListener {
                itemClickListener!!.onItemClicked(item.number)
            }
            binding.tvItemText.text = item.name
            binding.ifbItemBorder.backgroundTintList = ColorStateList.valueOf(item.color)
            binding.ivItemLock.visibility = if(item.isUnlocked) View.GONE else View.VISIBLE
            binding.ivItemCheck.visibility = if(cItem == position) View.VISIBLE else View.GONE
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
        holder.bind(items[position], position)
    }

    fun setData(list:List<BorderEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun setCheckedItem(cItem: Int) {
        this.cItem = cItem
        notifyDataSetChanged()
    }
}