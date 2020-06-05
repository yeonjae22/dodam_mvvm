package com.yeonproject.dodam_mvvm.view.word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import kotlinx.android.synthetic.main.item_word.view.*


class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var items = mutableListOf<WordItem>()
    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(word: WordItem)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        WordViewHolder(parent)

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) =
        holder.bind(items[position], onClickListener)

    fun addData(addDataList: List<WordItem>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class WordViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
    ) {
        fun bind(item: WordItem, listener: OnClickListener?) {

            itemView.run {
                setOnClickListener {
                    listener?.onClick(item)
                }
                iv_image.glideImageSet(
                    item.image,
                    iv_image.measuredWidth,
                    iv_image.measuredHeight
                )
            }
        }
    }
}