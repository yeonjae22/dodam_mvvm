package com.yeonproject.dodam_mvvm.view.song.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.ItemSongBinding
import com.yeonproject.dodam_mvvm.network.model.SongResponse


class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    private var items = mutableListOf<SongResponse>()
    private lateinit var binding: ItemSongBinding
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(song: SongResponse)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_song,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], listener)

    fun addData(addDataList: List<SongResponse>) {
        items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: SongResponse, listener: OnClickListener?) {
            binding.run {
                songResponse = item
                itemView.setOnClickListener {
                    listener?.onClick(item)
                }
            }
        }
    }
}