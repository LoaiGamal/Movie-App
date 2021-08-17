package com.example.android.imdbdemo.ui.upComing

import com.example.android.imdbdemo.data.repos.MoviesRepo
import com.example.android.imdbdemo.ui.baseMovieList.BaseMoviesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpComingViewModel(moviesRepo: MoviesRepo) :BaseMoviesListViewModel(moviesRepo) {

    override fun getMovies() = handleApiCall {
        val response =
            withContext(Dispatchers.IO){
                moviesRepo.getMovies(
                    "upcoming",
                    currentPage,
                    "popularity.desc"
                )
            }

        totalPages = response.totalPages

        movies.value?.addAll(response.details)
        movies.value = movies.value
    }
}
