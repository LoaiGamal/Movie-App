package com.example.android.imdbdemo.data.repos

import com.example.android.imdbdemo.data.model.BasePagination
import com.example.android.imdbdemo.data.model.TvResult
import com.example.android.imdbdemo.data.remote.TmdbEndPoints

interface TvRepo {
    suspend fun getTvById(id: Int): TvResult
    suspend fun getTvRecommendations(id:Int): BasePagination<TvResult>
}

class TvRepoImpl(private val service: TmdbEndPoints) : TvRepo {
    override suspend fun getTvById(id: Int): TvResult {
        return service.getTvById(id, "15e055caa208cab4ce3a30c8d5ac4a1a")
    }

    override suspend fun getTvRecommendations(id: Int): BasePagination<TvResult> {
        return service.getTvRecommendations(id, "15e055caa208cab4ce3a30c8d5ac4a1a")
    }
}