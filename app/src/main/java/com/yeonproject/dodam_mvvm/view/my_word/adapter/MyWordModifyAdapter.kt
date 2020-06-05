package com.yeonproject.dodam_mvvm.view.my_word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import kotlinx.android.synthetic.main.item_my_word.view.*


class MyWordModifyAdapter : RecyclerView.Adapter<MyWordModifyAdapter.MyWordViewHolder>() {
    private var items = mutableListOf<MyWordEntity>()
    private lateinit var moreButtonListener: MoreButtonListener

    interface MoreButtonListener {
        fun bottomSheetDialog(myWord: MyWordEntity)
    }

    fun setMoreButtonListener(listener: MoreButtonListener) {
        moreButtonListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWordViewHolder =
        MyWordViewHolder(parent)

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: MyWordViewHolder, position: Int) =
        holder.bind(items[position], moreButtonListener)

    fun addData(addDataList: List<MyWordEntity>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    fun removeData(myWord: MyWordEntity) {
        val position = items.indexOf(myWord)
        items.remove(myWord)
        notifyItemRemoved(position)
    }

    class MyWordViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_my_word, parent, false)
    ) {
        fun bind(item: MyWordEntity, moreButtonListener: MoreButtonListener) {
            itemView.run {
                btn_more.setOnClickListener {
                    moreButtonListener.bottomSheetDialog(item)
                }
                tv_hangul.text = item.hangul
                tv_english.text = item.english
                val image = context.filesDir.absoluteFile.toString() + "/" + item.image
                iv_image.glideImageSet(image, iv_image.measuredWidth, iv_image.measuredHeight)
            }
        }
    }
}