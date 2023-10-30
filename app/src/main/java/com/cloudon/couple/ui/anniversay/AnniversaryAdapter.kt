package com.cloudon.couple.ui.anniversay

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cloudon.couple.databinding.ViewAnniversaryItemBinding

class AnniversaryAdapter(private val context: Context) :
    RecyclerView.Adapter<AnniversaryAdapter.AnniversaryViewHolder>() {

    private var mData = mutableListOf<AnniversaryBean>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnniversaryViewHolder {
        return AnniversaryViewHolder(
            ViewAnniversaryItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: AnniversaryViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    fun setData(data: MutableList<AnniversaryBean>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    class AnniversaryViewHolder(val binding: ViewAnniversaryItemBinding) : ViewHolder(binding.root) {

        fun bind(bean: AnniversaryBean) {
            binding.bean = bean
        }

    }
}