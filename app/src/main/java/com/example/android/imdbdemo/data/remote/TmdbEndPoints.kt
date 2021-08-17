package com.example.android.imdbdemo.data.remote

import com.example.android.imdbdemo.data.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbEndPoints {
    @GET("movie/{category}")
    suspend fun getMovie(
        @Path("category") category: String,
        @Query("api_key") key: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String,
    ): BasePagination<Movie> //Call<MoviesResponse>


    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
        //@Path("recommendations") recommends: String,
        @Query("api_key") key: String
    ): Movie

    @GET("movie/{movie_id}/recommendations")
    suspend fun  getRecommendations(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): BasePagination<Movie>

    @GET("search/multi")
    suspend fun searchResult(
        @Query("api_key") key: String,
        @Query("query") query: String
    ): BasePagination<SearchingResult>

    @GET("tv/{id}")
    suspend fun getTvById(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ): TvResult

    @GET("tv/{tv_id}/recommendations")
    suspend fun  getTvRecommendations(
        @Path("tv_id") id: Int,
        @Query("api_key") key: String
    ): BasePagination<TvResult>

    @GET("person/{id}")
    suspend fun getPersonById(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ): Person
}