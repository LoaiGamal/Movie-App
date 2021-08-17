package com.example.android.imdbdemo.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android.imdbdemo.ui.MainActivity

open class BaseFragment : Fragment() {

    fun handleLoading(viewModel: BaseViewModel) {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, {
            if (it)
                (requireActivity() as MainActivity).showLoading()
            else
                (requireActivity() as MainActivity).hideLoading()
        })
    }



    fun showError(viewModel: BaseViewModel) {
        viewModel.errorLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}