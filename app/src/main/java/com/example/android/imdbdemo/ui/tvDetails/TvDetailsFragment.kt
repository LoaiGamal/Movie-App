package com.example.android.imdbdemo.ui.tvDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.data.model.TvResult
import com.example.android.imdbdemo.ui.adapters.TvRecommendationAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.coverImage
import kotlinx.android.synthetic.main.fragment_details.dateContent
import kotlinx.android.synthetic.main.fragment_details.languageContent
import kotlinx.android.synthetic.main.fragment_details.mTitle
import kotlinx.android.synthetic.main.fragment_details.moviePoster
import kotlinx.android.synthetic.main.fragment_details.overViewContent
import kotlinx.android.synthetic.main.fragment_details.rating
import kotlinx.android.synthetic.main.fragment_tv_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvDetailsFragment : Fragment() {

    private lateinit var tvRecommendationAdapter: TvRecommendationAdapter
    private val args: TvDetailsFragmentArgs by navArgs()
    private val viewModel: TvDetailsModelView by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = args.id


       setRecyclerView()

        observeData(id)
    }

    private fun observeData(id: Int){
        viewModel.getTheTvProgram(id).observe(viewLifecycleOwner, {
            setData(it)
        })
    }


    fun setData(detail: TvResult) {
        val picasso = Picasso.get()

        picasso.load("https://image.tmdb.org/t/p/w500${detail.posterPath}")
            .placeholder(R.drawable.no_image_available)
            .into(moviePoster)

        picasso.load("https://image.tmdb.org/t/p/w500${detail.backdropPath}")
            .placeholder(R.drawable.no_image_available)
            .into(coverImage)

        rating.text = detail.voteAverage.toString()
        mTitle.text = detail.name
        overViewContent.text = detail.overview
        languageContent.text = detail.originalLanguage
        dateContent.text = detail.firstAirDate

        viewModel.getRecommendations().observe(viewLifecycleOwner, {
            tvRecommendationAdapter.submitList(it.toMutableList())
        })

    }

    private fun setRecyclerView() {
        tvRecommendationAdapter = TvRecommendationAdapter {
        }
        tvRecommend.apply {
            adapter = tvRecommendationAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }
}