package com.example.android.imdbdemo.ui.details

import androidx.lifecycle.liveData
import com.example.android.imdbdemo.data.model.Movie
import com.example.android.imdbdemo.data.repos.DetailsRepo
import com.example.android.imdbdemo.data.repos.MoviesRepo
import com.example.android.imdbdemo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DetailViewModel(private val detailsRepo: DetailsRepo, private val moviesRepo: MoviesRepo) :
    BaseViewModel(), CoroutineScope {

    var tempID = 0
    var detailJob = Job()
    override val coroutineContext: CoroutineContext
        get() = detailJob + Dispatchers.Main


    fun getTheMovie(movieID: Int) = liveData<Movie> {
        try {
            val response =
                withContext(Dispatchers.IO) {
                    detailsRepo.getMovieById(
                        movieID
                    )
                }

            tempID = movieID
            emit(response)
        } catch (e: Exception) {
            e.message
        }
    }

    fun getRecommendations() = callApiLiveData {
            moviesRepo.getRecommendations(tempID).details
    }
}