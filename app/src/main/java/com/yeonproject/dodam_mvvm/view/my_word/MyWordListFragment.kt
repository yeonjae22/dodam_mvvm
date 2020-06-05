package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.my_word.adapter.MyWordModifyAdapter
import com.yeonproject.dodam_mvvm.view.my_word.presenter.MyWordListContract
import com.yeonproject.dodam_mvvm.view.my_word.presenter.MyWordListPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_my_word_list.*


class MyWordListFragment : Fragment(), MyWordListContract.View {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    override lateinit var presenter: MyWordListContract.Presenter
    private val myWordAdapter = MyWordModifyAdapter()
    private lateinit var listener: OnClickListener
    lateinit var myWordEntity: MyWordEntity

    override fun showDeleteResult(response: Boolean) {
        if (response) {
            context?.shortToast(R.string.success_delete_word)
        }
    }

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun showMyWordList(response: List<MyWordEntity>) {
        if (response.isEmpty()) {
            layout_progress_bar.visibility = View.GONE
            layout_has_my_word.visibility = View.GONE
            layout_no_my_word.visibility = View.VISIBLE
        } else {
            layout_progress_bar.visibility = View.GONE
            layout_has_my_word.visibility = View.VISIBLE
            layout_no_my_word.visibility = View.GONE
            myWordAdapter.addData(response)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_my_word_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = MyWordListPresenter(
            Injection.myWordRepository(), this
        )
        presenter.getMyWordList()
        setAdapter()

        btn_back.setOnClickListener {
            dispatcher.onBackPressed()
        }

        myWordAdapter.setMoreButtonListener(object : MyWordModifyAdapter.MoreButtonListener {
            override fun bottomSheetDialog(myWord: MyWordEntity) {
                myWordEntity = myWord
                val bottomSheetDialogFragment = MoreButtonFragment()
                bottomSheetDialogFragment.setTargetFragment(this@MyWordListFragment, REQUEST_CODE)
                fragmentManager?.let {
                    bottomSheetDialogFragment.show(
                        it,
                        bottomSheetDialogFragment.tag
                    )
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MODIFY_RESULT_CODE) {
                listener.onClick(WordModifyFragment.newInstance(myWordEntity.wordNumber))
            } else if (resultCode == DELETE_RESULT_CODE) {
                presenter.deleteMyWord(myWordEntity.wordNumber)
                myWordAdapter.removeData(myWordEntity)
            }
        }
    }

    private fun setAdapter() {
        recycler_my_word.layoutManager = LinearLayoutManager(context)
        recycler_my_word.adapter = myWordAdapter
    }

    companion object {
        const val REQUEST_CODE = 0
        const val MODIFY_RESULT_CODE = 1
        const val DELETE_RESULT_CODE = 2
    }
}