package com.example.android.imdbdemo.ui.topRated

import androidx.navigation.fragment.findNavController
import com.example.android.imdbdemo.ui.baseMovieList.BaseMovieListFragment
import com.example.android.imdbdemo.ui.baseMovieList.BaseMoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopRatedFragment : BaseMovieListFragment() {

    private val viewModel: TopRatedViewModel by viewModel()

    override val baseViewModel: BaseMoviesListViewModel
        get() = viewModel

    override fun goToMovieDetails(movieID: Int) {
        findNavController().navigate(
            TopRatedFragmentDirections.actionTopRatingToDetailsFragment(
                movieID
            )
        )
    }
}