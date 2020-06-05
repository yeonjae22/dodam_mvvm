package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.kroegerama.imgpicker.BottomSheetImagePicker
import com.kroegerama.imgpicker.ButtonType
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.my_word.presenter.WordModifyContract
import com.yeonproject.dodam_mvvm.view.my_word.presenter.WordModifyPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_word_modify.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class WordModifyFragment : Fragment(), WordModifyContract.View,
    BottomSheetImagePicker.OnImagesSelectedListener {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    override lateinit var presenter: WordModifyContract.Presenter
    private var wordNumber: Int = 0
    private var imageName: String = ""
    private var savedImageName: String = ""
    private var imageUri: Uri? = null

    override fun onImagesSelected(uris: List<Uri>, tag: String?) {
        imageContainer.removeAllViews()
        uris.forEach { uri ->
            val iv = LayoutInflater.from(context).inflate(
                R.layout.scrollitem_image,
                imageContainer,
                false
            ) as ImageView
            imageContainer.addView(iv)
            iv.findViewById<ImageView>(R.id.iv_image).run {
                glideImageSet(uri, measuredWidth, measuredHeight)
            }
        }
        if (!uris[0].path.isNullOrEmpty()) {
            imageName = uris[0].path.toString().split("/").last()
            imageUri = uris[0]
        }

        ib_delete.visibility = View.VISIBLE
        ib_image.visibility = View.GONE
    }

    override fun showMyWord(response: MyWordEntity) {
        edt_hangul_name.text = SpannableStringBuilder(response.hangul)
        edt_english_name.text = SpannableStringBuilder(response.english)
        imageContainer.removeAllViews()
        val iv = LayoutInflater.from(context).inflate(
            R.layout.scrollitem_image,
            imageContainer,
            false
        ) as ImageView
        imageContainer.addView(iv)
        val image = context?.filesDir?.absoluteFile.toString() + "/" + response.image
        iv.findViewById<ImageView>(R.id.iv_image).run {
            glideImageSet(image, measuredWidth, measuredHeight)
        }
        savedImageName = response.image
    }

    override fun showModifyResult(response: Boolean) {
        if (response) {
            context?.shortToast(R.string.success_update_word)
            dispatcher.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_word_modify, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ib_delete.visibility = View.VISIBLE
        ib_image.visibility = View.GONE
        wordNumber = arguments?.getInt(WORD_NUMBER) ?: 0
        presenter = WordModifyPresenter(
            Injection.myWordRepository(), this
        )
        presenter.getMyWord(wordNumber)

        ib_delete.setOnClickListener {
            savedImageName = ""
            imageContainer.removeAllViews()
            ib_delete.visibility = View.GONE
            ib_image.visibility = View.VISIBLE
        }

        ib_image.setOnClickListener {
            pickSingle()
        }

        btn_back.setOnClickListener {
            dispatcher.onBackPressed()
        }

        btn_modify.setOnClickListener {
            when {
                "${edt_hangul_name.text}".isEmpty() -> context?.shortToast(R.string.enter_hangul)
                "${edt_english_name.text}".isEmpty() -> context?.shortToast(R.string.enter_english)
                else -> {
                    if (imageName == "" && savedImageName == "") {
                        context?.shortToast(R.string.select_image)
                    } else if (imageName == "" && savedImageName != "") {
                        presenter.updateMyWord(
                            wordNumber,
                            "${edt_hangul_name.text}",
                            "${edt_english_name.text}"
                        )
                    } else {
                        createFile()
                        presenter.updateMyWord(
                            wordNumber,
                            "${edt_hangul_name.text}",
                            "${edt_english_name.text}",
                            imageName
                        )
                    }
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

    companion object {
        private const val WORD_NUMBER = "wordNumber"
        fun newInstance(wordNumber: Int) = WordModifyFragment().apply {
            arguments = Bundle().apply {
                putInt(WORD_NUMBER, wordNumber)
            }
        }
    }
}