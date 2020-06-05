package com.yeonproject.dodam_mvvm.view.song.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.network.model.SongResponse
import kotlinx.android.synthetic.main.item_song.view.*


class SongAdapter : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    private var items = mutableListOf<SongResponse>()
    private var onClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onClick(song: SongResponse)
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
        SongViewHolder(parent)

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.bind(items[position], onClickListener)

    fun addData(addDataList: List<SongResponse>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class SongViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
    ) {
        fun bind(item: SongResponse, listener: OnClickListener?) {
            itemView.run {
                setOnClickListener {
                    listener?.onClick(item)
                }
                iv_image.glideImageSet(
                    item.image,
                    iv_image.measuredWidth,
                    iv_image.measuredHeight
                )
                tv_name.text = item.name
            }
        }
    }
}