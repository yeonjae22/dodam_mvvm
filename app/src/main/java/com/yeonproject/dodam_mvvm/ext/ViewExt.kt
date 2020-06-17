package com.yeonproject.dodam_mvvm.ext

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageLoad")
fun ImageView.glideImageSet(image: String) {
    Glide.with(context)
        .load(image)
        .override(measuredWidth, measuredHeight)
        .centerCrop()
        .into(this)
}

@BindingAdapter("imageLoad")
fun ImageView.glideImageSet(image: Uri) {
    Glide.with(context)
        .load(image)
        .override(measuredWidth, measuredHeight)
        .centerCrop()
        .into(this)
}

fun Context.shortToast(resourceId: Int) {
    Toast.makeText(this, resourceId, Toast.LENGTH_SHORT)
        .show()
}