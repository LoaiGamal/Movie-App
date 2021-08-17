package com.example.android.imdbdemo.ui.searching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.ui.SearchingAdapter
import com.example.android.imdbdemo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_searching.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchingFragment : BaseFragment() {
    private val args: SearchingFragmentArgs by navArgs()

    private lateinit var searchingAdapter: SearchingAdapter
    private val viewModel: SearchingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_searching, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchingAdapter = SearchingAdapter { id, type ->
            when (type) {
                "movie" -> getMovieDetails(id)
                "tv" -> getTvDetails(id)
                "person" -> getPersonDetails(id)
            }
        }


        searchingRecyclerView.apply {
            adapter = searchingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        args.searchQuery?.let { query ->
            viewModel.getSearchResult(query).observe(viewLifecycleOwner, {
                if (it.size != 0) {
                    resultFound.visibility = View.GONE
                    searchingAdapter.submitList(it.toMutableList())
                }else{
                    resultFound.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun getMovieDetails(movieId: Int) {
        findNavController().navigate(
            SearchingFragmentDirections.actionSearchingFragmentToDetailsFragment(movieId)
        )
    }

    private fun getTvDetails(tvId: Int) {
        findNavController().navigate(
            SearchingFragmentDirections.actionSearchingFragmentToTvDetailsFragment(tvId)
        )
    }

    private fun getPersonDetails(personId: Int) {
        findNavController().navigate(
            SearchingFragmentDirections.actionSearchingFragmentToPersonFragment(personId)
        )
    }
}