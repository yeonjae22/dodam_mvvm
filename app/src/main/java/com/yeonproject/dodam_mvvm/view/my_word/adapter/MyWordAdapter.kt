package com.yeonproject.dodam_mvvm.view.my_word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.MyWordItem
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.databinding.ItemMyWordBinding
import com.yeonproject.dodam_mvvm.ext.glideImageSet


class MyWordAdapter : RecyclerView.Adapter<MyWordAdapter.MyWordViewHolder>() {
    private var items = mutableListOf<MyWordItem>()
    private lateinit var binding: ItemMyWordBinding
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(myWord: MyWordItem)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWordViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_my_word,
            parent,
            false
        )

        return MyWordViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: MyWordViewHolder, position: Int) =
        holder.bind(items[position], listener)

    fun addData(addDataList: List<MyWordItem>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class MyWordViewHolder(private val binding: ItemMyWordBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: MyWordItem, listener: OnClickListener?) {
            binding.run {
                itemView.run {
                    setOnClickListener {
                        listener?.onClick(item)
                    }
                }
                myWordItem = item
            }
        }
    }
}