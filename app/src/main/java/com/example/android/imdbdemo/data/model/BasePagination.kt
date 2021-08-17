package com.example.android.imdbdemo.data.model


import com.google.gson.annotations.SerializedName

data class BasePagination<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val details: MutableList<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)