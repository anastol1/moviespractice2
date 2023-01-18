package com.example.moviespractice2.network

import com.google.gson.annotations.SerializedName

class ResultsListModel {
    @SerializedName("page")
    var page: Int? = null

    @SerializedName("results")
    var results: List<MovieModel>? = null
}