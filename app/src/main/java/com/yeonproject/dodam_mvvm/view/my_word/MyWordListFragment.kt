package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.databinding.FragmentMyWordListBinding
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.my_word.adapter.MyWordModifyAdapter
import com.yeonproject.dodam_mvvm.view.view_model.MyWordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyWordListFragment : BaseFragment<FragmentMyWordListBinding>(R.layout.fragment_my_word_list) {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    private val myWordAdapter = MyWordModifyAdapter()
    private lateinit var listener: OnClickListener
    lateinit var myWordEntity: MyWordEntity
    private val viewModel by viewModel<MyWordViewModel>()

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMyWordList()
        showMyWordList()
        setAdapter()

        binding.btnBack.setOnClickListener {
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
                viewModel.deleteMyWord(myWordEntity.wordNumber)
                showMessage()
                myWordAdapter.removeData(myWordEntity)
            }
        }
    }

    private fun showMessage() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            if (it) {
                context?.shortToast(R.string.success_delete_word)
            }
        })
    }

    private fun showMyWordList() {
        viewModel.myWordList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.layoutProgressBar.visibility = View.GONE
                binding.layoutHasMyWord.visibility = View.GONE
                binding.layoutNoMyWord.visibility = View.VISIBLE
            } else {
                binding.layoutProgressBar.visibility = View.GONE
                binding.layoutHasMyWord.visibility = View.VISIBLE
                binding.layoutNoMyWord.visibility = View.GONE
                myWordAdapter.addData(it)
            }
        })
    }

    private fun setAdapter() {
        binding.recyclerMyWord.layoutManager = LinearLayoutManager(context)
        binding.recyclerMyWord.adapter = myWordAdapter
    }

    companion object {
        const val REQUEST_CODE = 0
        const val MODIFY_RESULT_CODE = 1
        const val DELETE_RESULT_CODE = 2
    }
}