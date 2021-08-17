package com.example.android.imdbdemo.modules

import com.example.android.imdbdemo.ui.details.DetailViewModel
import com.example.android.imdbdemo.ui.nowPlaying.NowPlayingViewModel
import com.example.android.imdbdemo.ui.personDetails.PersonViewModel
import com.example.android.imdbdemo.ui.searching.SearchingViewModel
import com.example.android.imdbdemo.ui.topRated.TopRatedViewModel
import com.example.android.imdbdemo.ui.tvDetails.TvDetailsModelView
import com.example.android.imdbdemo.ui.upComing.UpComingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TopRatedViewModel(get())
    }

    viewModel {
        NowPlayingViewModel(get())
    }

    viewModel {
        UpComingViewModel(get())
    }

    viewModel {
        DetailViewModel(get(), get())
    }

    viewModel {
        SearchingViewModel(get())
    }

    viewModel {
        TvDetailsModelView(get())
    }

    viewModel {
        PersonViewModel(get())
    }

}
