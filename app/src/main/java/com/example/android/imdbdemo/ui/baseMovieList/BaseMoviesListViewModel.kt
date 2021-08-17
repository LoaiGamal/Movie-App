package com.example.android.imdbdemo.ui.baseMovieList

import androidx.lifecycle.MutableLiveData
import com.example.android.imdbdemo.ui.base.BaseViewModel
import com.example.android.imdbdemo.data.model.Movie
import com.example.android.imdbdemo.data.repos.MoviesRepo

abstract class BaseMoviesListViewModel(val moviesRepo: MoviesRepo) : BaseViewModel() {

    abstract fun getMovies()

    val movies = MutableLiveData<ArrayList<Movie>>()

    var currentPage = 0
    var totalPages = 1

    init {
        movies.value = ArrayList<Movie>()
    }


    fun loadNextPage() {
        if (!loadingLiveData.value!! && currentPage < totalPages) {
            currentPage++
            getMovies()
        }
    }
}