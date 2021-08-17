package com.example.android.imdbdemo.ui.tvDetails

import androidx.lifecycle.liveData
import com.example.android.imdbdemo.data.model.TvResult
import com.example.android.imdbdemo.data.repos.TvRepo
import com.example.android.imdbdemo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TvDetailsModelView(private val tvRepo: TvRepo): BaseViewModel(), CoroutineScope {
    var tempID = 0
    var detailJob = Job()
    override val coroutineContext: CoroutineContext
        get() = detailJob + Dispatchers.Main

    fun getTheTvProgram(tvID: Int) = liveData<TvResult> {
        try {
            val response =
                withContext(Dispatchers.IO) {
                    tvRepo.getTvById(
                        tvID
                    )
                }

            tempID = tvID
            emit(response)
        } catch (e: Exception) {
            e.message
        }
    }

    fun getRecommendations() = callApiLiveData {
        tvRepo.getTvRecommendations(tempID).details
    }
}