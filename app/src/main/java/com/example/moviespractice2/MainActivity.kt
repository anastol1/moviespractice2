package com.example.moviespractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.moviespractice2.network.API
import com.example.moviespractice2.network.MovieModel
import com.example.moviespractice2.network.ResultsListModel
import com.example.moviespractice2.network.ServiceBuilder
import com.facebook.drawee.backends.pipeline.Fresco
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fresco.initialize(this)

        ServiceBuilder.buildService().getMovies()
            .flatMap { results -> Observable.fromIterable(results.results) }
            .filter { it.adult == false }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it) },
                { error -> println(error.message) }
            )
    }

    private fun onSuccess(results: List<MovieModel>) {
        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = MoviesAdapter(results)
    }

}