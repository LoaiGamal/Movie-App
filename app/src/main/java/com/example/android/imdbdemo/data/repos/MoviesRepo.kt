package com.example.android.imdbdemo.data.repos

import com.example.android.imdbdemo.data.model.BasePagination
import com.example.android.imdbdemo.data.model.Movie
import com.example.android.imdbdemo.data.remote.TmdbEndPoints

interface MoviesRepo {
    suspend fun getMovies(category: String, page: Int, sort: String): BasePagination<Movie>
    suspend fun getRecommendations(id: Int): BasePagination<Movie>
}

class MoviesRepoImpl(private val service: TmdbEndPoints) : MoviesRepo {
    override suspend fun getMovies(category: String, page: Int, sort: String): BasePagination<Movie> {
        return service.getMovie(category, "your key", page, sort)
    }

    override suspend fun getRecommendations(id: Int): BasePagination<Movie> {
        return service.getRecommendations(id, "your key")
    }
}