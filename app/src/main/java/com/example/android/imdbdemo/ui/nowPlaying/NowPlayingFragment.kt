package com.example.android.imdbdemo.ui.nowPlaying

import androidx.navigation.fragment.findNavController
import com.example.android.imdbdemo.ui.baseMovieList.BaseMovieListFragment
import com.example.android.imdbdemo.ui.baseMovieList.BaseMoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NowPlayingFragment : BaseMovieListFragment(){


    private val viewModel: NowPlayingViewModel by viewModel()

    override val baseViewModel: BaseMoviesListViewModel
        get() = viewModel

    override fun goToMovieDetails(movieID: Int) {
        findNavController().navigate(
            NowPlayingFragmentDirections.actionNowPlayingToDetailsFragment(
                movieID
            )
        )
    }
}