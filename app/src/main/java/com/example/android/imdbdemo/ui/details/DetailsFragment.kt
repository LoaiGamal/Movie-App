package com.example.android.imdbdemo.ui.details


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.data.model.Movie
import com.example.android.imdbdemo.ui.baseMovieList.RecommendationsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var recommendationsAdapter: RecommendationsAdapter
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = args.id

        setRecyclerView()

        observeData(id)
    }

    private fun observeData(id: Int){
        viewModel.getTheMovie(id).observe(viewLifecycleOwner, {
            setData(it)
        })
    }


    fun setData(detail: Movie) {
        val picasso = Picasso.get()

        picasso.load("https://image.tmdb.org/t/p/w500${detail.posterPath}")
            .placeholder(R.drawable.no_image_available)
            .into(moviePoster)

        picasso.load("https://image.tmdb.org/t/p/w500${detail.backdropPath}")
            .placeholder(R.drawable.no_image_available)
            .into(coverImage)

        rating.text = detail.voteAverage.toString()
        mTitle.text = detail.originalTitle
        overViewContent.text = detail.overview
        languageContent.text = detail.originalLanguage
        dateContent.text = detail.releaseDate

        viewModel.getRecommendations().observe(viewLifecycleOwner, {
            recommendationsAdapter.submitList(it.toMutableList())
        })

    }

    private fun setRecyclerView() {
        recommendationsAdapter = RecommendationsAdapter {
            observeData(it)
        }
        recommend.apply {
            adapter = recommendationsAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }


}