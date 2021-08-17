package com.example.android.imdbdemo.ui.searching

import androidx.lifecycle.MutableLiveData
import com.example.android.imdbdemo.data.repos.SearchRepo
import com.example.android.imdbdemo.ui.base.BaseViewModel

class SearchingViewModel(private val searchingRepo: SearchRepo) : BaseViewModel() {


    fun getSearchResult(search: String) = callApiLiveData {
        searchingRepo.getSearchingResult(search).details
    }
}