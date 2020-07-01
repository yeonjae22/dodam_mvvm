package com.yeonproject.dodam_mvvm.view.my_word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.MyWordItem
import com.yeonproject.dodam_mvvm.databinding.ItemMyWordBinding
import com.yeonproject.dodam_mvvm.databinding.ItemMyWordModifyBinding


class MyWordModifyAdapter : RecyclerView.Adapter<MyWordModifyAdapter.ViewHolder>() {
    private var items = mutableListOf<MyWordItem>()
    private lateinit var binding: ItemMyWordModifyBinding
    private lateinit var listener: MoreButtonListener

    interface MoreButtonListener {
        fun bottomSheetDialog(myWord: MyWordItem)
    }

    fun setMoreButtonListener(listener: MoreButtonListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyWordModifyAdapter.ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_my_word_modify,
            parent,
            false
        )

        return MyWordModifyAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], listener)

    fun addData(addDataList: List<MyWordItem>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    fun removeData(myWord: MyWordItem) {
        val position = items.indexOf(myWord)
        items.remove(myWord)
        notifyItemRemoved(position)
    }

    class ViewHolder(private val binding: ItemMyWordModifyBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: MyWordItem, listener: MoreButtonListener) {
            binding.run {
                btnMore.setOnClickListener {
                    listener.bottomSheetDialog(item)
                }
                myWordItem = item
            }
        }
    }
}