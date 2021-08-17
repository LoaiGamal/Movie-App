package com.example.android.imdbdemo.ui.baseMovieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.ui.base.BaseFragment
import com.example.android.imdbdemo.ui.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_top_rated.*

abstract class BaseMovieListFragment : BaseFragment() {
    private lateinit var moviesAdapter: MoviesAdapter

    abstract val baseViewModel: BaseMoviesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleLoading(baseViewModel)

        showError(baseViewModel)

        initMoviesList()

        onScrollerListener()

        handleMoviesLiveData()

        baseViewModel.loadNextPage()

    }

    private fun initMoviesList() {
        moviesAdapter = MoviesAdapter() {
            goToMovieDetails(it)
        }


        topRatedRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }


    }

    private fun handleMoviesLiveData() {
        baseViewModel.movies.observe(viewLifecycleOwner, {
            moviesAdapter.submitList(it.toMutableList())
        })
    }

    abstract fun goToMovieDetails(movieID: Int)

    private fun onScrollerListener() {
        topRatedRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val firstVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    baseViewModel.loadNextPage()
                }
            }
        })
    }

}