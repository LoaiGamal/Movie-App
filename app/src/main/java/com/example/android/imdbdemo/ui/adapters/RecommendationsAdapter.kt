package com.example.android.imdbdemo.ui.baseMovieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.data.model.Movie
import com.squareup.picasso.Picasso
import kotlin.math.floor

class RecommendationsAdapter(val click: (id: Int) -> Unit) :
    ListAdapter<Movie, MoviesViewHolder>(MovieDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recommendation_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        val picasso = Picasso.get()
        picasso.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.photo)
        holder.title.text = movie.title
        holder.rating.text = roundNum(movie.voteAverage).toString()
        holder.itemView.setOnClickListener {
            click(movie.id)
        }
    }

    fun roundNum(rating: Double): Double {
        var temp = rating * 10
        temp += 0.5
        temp = floor(temp) / 10
        return temp
    }
}


class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val photo: ImageView = itemView.findViewById(R.id.movieImage)
    val title: TextView = itemView.findViewById(R.id.movieTitle)
    val rating: TextView = itemView.findViewById(R.id.movieRating)
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}