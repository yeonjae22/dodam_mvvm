package com.yeonproject.dodam_mvvm.view.my_word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import kotlinx.android.synthetic.main.item_word.view.*


class MyWordAdapter : RecyclerView.Adapter<MyWordAdapter.MyWordViewHolder>() {
    private var items = mutableListOf<MyWordEntity>()
    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(myWord: MyWordEntity)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWordViewHolder =
        MyWordViewHolder(parent)

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: MyWordViewHolder, position: Int) =
        holder.bind(items[position], onClickListener)

    fun addData(addDataList: List<MyWordEntity>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class MyWordViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
    ) {
        fun bind(item: MyWordEntity, listener: OnClickListener?) {

            itemView.run {
                setOnClickListener {
                    listener?.onClick(item)
                }
                val image = context.filesDir.absoluteFile.toString() + "/" + item.image
                iv_image.glideImageSet(image, iv_image.measuredWidth, iv_image.measuredHeight)
            }
        }
    }
}