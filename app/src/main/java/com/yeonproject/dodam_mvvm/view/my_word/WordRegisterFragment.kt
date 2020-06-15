package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.kroegerama.imgpicker.BottomSheetImagePicker
import com.kroegerama.imgpicker.ButtonType
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentWordRegisterBinding
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.view_model.MyWordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class WordRegisterFragment :
    BaseFragment<FragmentWordRegisterBinding>(R.layout.fragment_word_register),
    BottomSheetImagePicker.OnImagesSelectedListener {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    private var imageName: String = ""
    private var imageUri: Uri? = null
    private val viewModel by viewModel<MyWordViewModel>()

    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        binding.imageContainer.removeAllViews()
        uris.forEach { uri ->
            val iv = LayoutInflater.from(context).inflate(
                R.layout.scrollitem_image,
                binding.imageContainer,
                false
            ) as ImageView
            binding.imageContainer.addView(iv)
            iv.findViewById<ImageView>(R.id.iv_image).run {
                glideImageSet(uri)
            }
        }
        if (!uris[0].path.isNullOrEmpty()) {
            imageName = uris[0].path.toString().split("/").last()
            imageUri = uris[0]
        }

        binding.ibDelete.visibility = View.VISIBLE
        binding.ibImage.visibility = View.GONE
        binding.ibDelete.setOnClickListener {
            imageName = ""
            imageUri = null
            binding.imageContainer.removeAllViews()
            binding.ibDelete.visibility = View.GONE
            binding.ibImage.visibility = View.VISIBLE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.ibImage.setOnClickListener {
            pickSingle()
        }

        binding.btnBack.setOnClickListener {
            dispatcher.onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            when {
                "${binding.edtHangulName.text}".isEmpty() -> context?.shortToast(R.string.enter_hangul)
                "${binding.edtEnglishName.text}".isEmpty() -> context?.shortToast(R.string.enter_english)
                imageName.isEmpty() -> context?.shortToast(R.string.select_image)
                else -> {
                    createFile()
                    viewModel.createMyWord(
                        0,
                        "${binding.edtHangulName.text}",
                        "${binding.edtEnglishName.text}",
                        imageName
                    )
                    showMessage()
                }
            }
        }
    }

    private fun showMessage() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            if (it) {
                context?.shortToast(R.string.success_register_word)
                dispatcher.onBackPressed()
            }
        })
    }

    private fun pickSingle() {
        BottomSheetImagePicker.Builder(getString(R.string.file_provider))
            .cameraButton(ButtonType.Button)
            .galleryButton(ButtonType.Button)
            .singleSelectTitle(R.string.select_image)
            .peekHeight(R.dimen.peekHeight)
            .columnSize(R.dimen.columnSize)
            .requestTag(getString(R.string.selected_image))
            .show(childFragmentManager)
    }

    private fun createFile() {
        try {
            context?.openFileOutput(imageName, Context.MODE_PRIVATE).use {
                it?.write(imageUri?.let { it1 -> readBytes(context, it1) })
            }

            val reader = BufferedReader(InputStreamReader(context?.openFileInput(imageName)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun readBytes(context: Context?, uri: Uri): ByteArray? =
        context?.contentResolver?.openInputStream(uri)?.buffered()?.use { it.readBytes() }
}