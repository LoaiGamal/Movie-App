package com.example.android.imdbdemo.ui.topRated

import com.example.android.imdbdemo.data.repos.MoviesRepo
import com.example.android.imdbdemo.ui.baseMovieList.BaseMoviesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopRatedViewModel(moviesRepo: MoviesRepo) : BaseMoviesListViewModel(moviesRepo) {
    override fun getMovies() = handleApiCall {
        val response =
            withContext(Dispatchers.IO) {
                moviesRepo.getMovies(
                    "top_rated",
                    currentPage,
                    "popularity.desc"
                )
            }

        totalPages = response.totalPages

        movies.value?.addAll(response.details)
        movies.value = movies.value
    }
}