package com.example.moviespractice2

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespractice2.network.MovieModel
import com.facebook.drawee.view.SimpleDraweeView

class MoviesAdapter(private val data: List<MovieModel>): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView
        val overview: TextView
        val poster: SimpleDraweeView
        val button: Button
        var isClicked = false

        init {
            title = view.findViewById(R.id.title)
            overview = view.findViewById(R.id.overview)
            poster = view.findViewById(R.id.poster)
            button = view.findViewById(R.id.button_guy)
            button.setOnClickListener { v ->
                if(isClicked) {
                    v.setBackgroundColor(Color.parseColor("#FF0000"))
                    isClicked = false
                } else {
                    v.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    isClicked = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies_recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.overview.text = data[position].overview
        holder.poster.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original" + data[position].posterPath))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}