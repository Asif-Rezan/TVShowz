package com.asifrezan.tvshowz.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.data.models.movies.MovieList
import com.asifrezan.tvshowz.data.models.movies.Movies
import com.asifrezan.tvshowz.utils.IMAGE_STORAGE_URL
import com.bumptech.glide.Glide

class SimilarMoviesAdapter(var movies: List<MovieList>) : RecyclerView.Adapter<SimilarMoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.row_obj_similar_movies, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePosterImageView: ImageView = itemView.findViewById(R.id.movie_poster)
        private val movieTitleTextView: TextView = itemView.findViewById(R.id.movie_title)


        fun bind(movie: MovieList) {

            val posterUrl = "$IMAGE_STORAGE_URL/${movie.poster_path}"

            Glide.with(itemView)
                .load(posterUrl)
                .placeholder(R.drawable.loading)
                .into(moviePosterImageView)

            movieTitleTextView.text = movie.title
        }
    }
}