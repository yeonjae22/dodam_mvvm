package com.yeonproject.dodam_mvvm.view.my_word.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.databinding.ItemMyWordBinding
import com.yeonproject.dodam_mvvm.ext.glideImageSet


class MyWordModifyAdapter : RecyclerView.Adapter<MyWordModifyAdapter.ViewHolder>() {
    private var items = mutableListOf<MyWordEntity>()
    private lateinit var binding: ItemMyWordBinding
    private lateinit var listener: MoreButtonListener

    interface MoreButtonListener {
        fun bottomSheetDialog(myWord: MyWordEntity)
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
            R.layout.item_my_word,
            parent,
            false
        )

        return MyWordModifyAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], listener)

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

    class ViewHolder(private val binding: ItemMyWordBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: MyWordEntity, listener: MoreButtonListener) {
            binding.run {
                itemView.run {
                    setOnClickListener {
                        listener.bottomSheetDialog(item)
                    }
                    tvHangul.text = item.hangul
                    tvEnglish.text = item.english
                    val image = context.filesDir.absoluteFile.toString() + "/" + item.image
                    binding.ivImage.glideImageSet(
                        image
                    )
                }
            }
        }
    }
}