package com.example.android.imdbdemo.data.repos

import com.example.android.imdbdemo.data.model.BasePagination
import com.example.android.imdbdemo.data.model.SearchingResult
import com.example.android.imdbdemo.data.model.TvResult
import com.example.android.imdbdemo.data.remote.TmdbEndPoints

interface SearchRepo {
    suspend fun getSearchingResult(query: String): BasePagination<SearchingResult>
}

class SearchRepoImpl (private val service: TmdbEndPoints) : SearchRepo{
    override suspend fun getSearchingResult(query: String): BasePagination<SearchingResult> {
        return service.searchResult("your key", query)
    }


}