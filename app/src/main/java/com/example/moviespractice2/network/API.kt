package com.example.moviespractice2.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface API {
    @GET("/3/movie/top_rated")
    fun getMovies(): Observable<ResultsListModel>
}