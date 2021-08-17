package com.example.android.imdbdemo.data.repos

import com.example.android.imdbdemo.data.model.Movie
import com.example.android.imdbdemo.data.remote.TmdbEndPoints


interface DetailsRepo {
    suspend fun getMovieById(id: Int): Movie
}

class DetailsRepoImpl(private val service: TmdbEndPoints) : DetailsRepo {
    override suspend fun getMovieById(id: Int): Movie {
        return service.getMovieById(id,"15e055caa208cab4ce3a30c8d5ac4a1a")
    }
}