package com.yeonproject.dodam_mvvm.view.word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.databinding.ItemWordBinding


class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    private var items = mutableListOf<WordItem>()
    private lateinit var binding: ItemWordBinding
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(word: WordItem)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_word,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], listener)

    fun addData(addDataList: List<WordItem>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: WordItem, listener: OnClickListener?) {
            binding.run {
                wordItem = item
                itemView.setOnClickListener {
                    listener?.onClick(item)
                }
            }
        }
    }
}