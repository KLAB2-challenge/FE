package com.example.klab2challenge.ui.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klab2challenge.databinding.ItemRecordListBinding
import com.example.klab2challenge.retrofit.GetProofPostResponse

class RecordListAdapter(var items: List<GetProofPostResponse>) : RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(recordId: Int)
    }

    private var onItemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class ViewHolder(val binding : ItemRecordListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : GetProofPostResponse) {
            binding.root.setOnClickListener {
                onItemClickListener!!.onItemClicked(item.proofPostId)
            }
            binding.tvItemRlTitle.text = item.contents.title
            binding.tvItemRlContent.text = item.contents.content
            binding.tvRdUserName.text = item.memberName
            binding.tvRdPostDate.text = item.infos.date
            binding.tvItemRlMessage.text = item.commentNum.toString()
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

    fun setData(list: List<GetProofPostResponse>) {
        items = list
        notifyDataSetChanged()
    }
}