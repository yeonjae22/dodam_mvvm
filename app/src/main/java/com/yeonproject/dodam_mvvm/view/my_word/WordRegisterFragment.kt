package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.kroegerama.imgpicker.BottomSheetImagePicker
import com.kroegerama.imgpicker.ButtonType
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.my_word.presenter.WordRegisterContract
import com.yeonproject.dodam_mvvm.view.my_word.presenter.WordRegisterPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_word_register.*
import kotlinx.android.synthetic.main.scrollitem_image.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class WordRegisterFragment : Fragment(), BottomSheetImagePicker.OnImagesSelectedListener,
    WordRegisterContract.View {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    override lateinit var presenter: WordRegisterContract.Presenter
    private var imageName: String = ""
    private var imageUri: Uri? = null

    override fun showMessage(response: Boolean) {
        if (response) {
            context?.shortToast(R.string.success_register_word)
            dispatcher.onBackPressed()
        }
    }

    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        imageContainer.removeAllViews()
        uris.forEach { uri ->
            val iv = LayoutInflater.from(context).inflate(
                R.layout.scrollitem_image,
                imageContainer,
                false
            ) as ImageView
            imageContainer.addView(iv)
            iv.iv_image.glideImageSet(uri, iv.iv_image.measuredWidth, iv.iv_image.measuredHeight)
        }
        if (!uris[0].path.isNullOrEmpty()) {
            imageName = uris[0].path.toString().split("/").last()
            imageUri = uris[0]
        }

        ib_delete.visibility = View.VISIBLE
        ib_image.visibility = View.GONE
        ib_delete.setOnClickListener {
            imageName = ""
            imageUri = null
            imageContainer.removeAllViews()
            ib_delete.visibility = View.GONE
            ib_image.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_word_register, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = WordRegisterPresenter(
            Injection.myWordRepository(), this
        )

        ib_image.setOnClickListener {
            pickSingle()
        }

        btn_back.setOnClickListener {
            dispatcher.onBackPressed()
        }

        btn_register.setOnClickListener {
            when {
                "${edt_hangul_name.text}".isEmpty() -> context?.shortToast(R.string.enter_hangul)
                "${edt_english_name.text}".isEmpty() -> context?.shortToast(R.string.enter_english)
                imageName.isEmpty() -> context?.shortToast(R.string.select_image)
                else -> {
                    createFile()
                    presenter.createMyWord(
                        0,
                        "${edt_hangul_name.text}",
                        "${edt_english_name.text}",
                        imageName
                    )
                }
            }
        }
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