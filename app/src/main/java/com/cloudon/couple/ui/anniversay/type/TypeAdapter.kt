package com.cloudon.couple.ui.anniversay.type

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudon.couple.databinding.ViewTypeItemBinding

class TypeAdapter(private val context: Context) :
    RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {

    private var mData = mutableListOf<TypeBean>()
    private var mSelectedData = HashSet<TypeBean>()

    private var listener: OnItemListener? = null

    fun setOnItemListener(listener: OnItemListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        return TypeViewHolder(
            ViewTypeItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mData.size

    fun getData() = mData

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.bind(mData[position])
        holder.binding.ivDelete.setOnClickListener {
            mSelectedData.add(mData[position])
            listener?.onDelete(position)
        }
        holder.binding.cb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mSelectedData.add(mData[position])
            } else {
                mSelectedData.remove(mData[position])
            }
        }
    }

    fun setData(data: MutableList<TypeBean>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun getSelectedData() = mSelectedData

    class TypeViewHolder(val binding: ViewTypeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bean: TypeBean) {
            binding.bean = bean
        }
    }

    interface OnItemListener {
        fun onDelete(position: Int)
    }

}