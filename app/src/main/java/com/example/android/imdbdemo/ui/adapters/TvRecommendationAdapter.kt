package com.example.android.imdbdemo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.data.model.TvResult
import com.squareup.picasso.Picasso
import kotlin.math.floor

class TvRecommendationAdapter(val click: (id: Int) -> Unit) :
    ListAdapter<TvResult, TvViewHolder>(TvDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tv_recommendation_item, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        val picasso = Picasso.get()
        picasso.load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
            .placeholder(R.drawable.no_image_available)
            .into(holder.photo)
        holder.title.text = tv.name
        holder.rating.text = roundNum(tv.voteAverage).toString()
        holder.itemView.setOnClickListener {
            click(tv.id)
        }
    }

    fun roundNum(rating: Double): Double {
        var temp = rating * 10
        temp += 0.5
        temp = floor(temp) / 10
        return temp
    }
}


class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val photo: ImageView = itemView.findViewById(R.id.movieImage)
    val title: TextView = itemView.findViewById(R.id.movieTitle)
    val rating: TextView = itemView.findViewById(R.id.movieRating)
}

class TvDiffCallBack : DiffUtil.ItemCallback<TvResult>() {
    override fun areItemsTheSame(oldItem: TvResult, newItem: TvResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TvResult, newItem: TvResult): Boolean {
        return oldItem == newItem
    }
}