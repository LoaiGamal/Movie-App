package com.example.android.imdbdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.data.model.SearchingResult
import com.squareup.picasso.Picasso

class SearchingAdapter(val click: (id: Int, type: String) -> Unit) :
    ListAdapter<SearchingResult, SearchingViewHolder>(SearchingDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchingViewHolder, position: Int) {
        val result = getItem(position)
        if (result.mediaType == "movie") {
            val picasso = Picasso.get()
            picasso.load("https://image.tmdb.org/t/p/w500${result.posterPath}")
                .into(holder.photo)
            holder.title.text = result.title
            holder.itemView.setOnClickListener {
                click(result.id, result.mediaType)
            }
        } else if (result.mediaType == "person") {
            val picasso = Picasso.get()
            picasso.load("https://image.tmdb.org/t/p/w500${result.profilePath}")
                .into(holder.photo)
            holder.title.text = result.name
            holder.itemView.setOnClickListener {
                click(result.id, result.mediaType)
            }

        }
        else if (result.mediaType == "tv") {
            val picasso = Picasso.get()
            picasso.load("https://image.tmdb.org/t/p/w500${result.posterPath}")
                .into(holder.photo)
            holder.title.text = result.name
            holder.itemView.setOnClickListener {
                click(result.id, result.mediaType)
            }

        }

    }
}


    class SearchingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.searchImage)
        val title: TextView = itemView.findViewById(R.id.searchTitle)
    }

    class SearchingDiffCallBack : DiffUtil.ItemCallback<SearchingResult>() {
        override fun areItemsTheSame(oldItem: SearchingResult, newItem: SearchingResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchingResult,
            newItem: SearchingResult
        ): Boolean {
            return oldItem == newItem
        }
    }