package com.example.android.imdbdemo.ui.upComing

import androidx.navigation.fragment.findNavController
import com.example.android.imdbdemo.ui.baseMovieList.BaseMovieListFragment
import com.example.android.imdbdemo.ui.baseMovieList.BaseMoviesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingFragment : BaseMovieListFragment() {
    private val viewModel: UpComingViewModel by viewModel()
    override val baseViewModel: BaseMoviesListViewModel
        get() = viewModel

    override fun goToMovieDetails(movieID: Int) {
        findNavController().navigate(
            UpcomingFragmentDirections.actionUpcomingFragmentToDetailsFragment(
                movieID
            )
        )
    }
}