package com.asifrezan.tvshowz.ui.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.ui.activities.DetailsActivity
import com.asifrezan.tvshowz.data.models.movies.MovieList
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriesList
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriseDetails
import com.asifrezan.tvshowz.ui.activities.TvSeriseDetailsActivity
import com.asifrezan.tvshowz.utils.IMAGE_STORAGE_URL
import com.bumptech.glide.Glide

class TvSeriseListAdapter(
    private val context: Context,
    private val tvSeriesList: MutableList<TvSeriesList> = mutableListOf()
) : BaseAdapter() {

    override fun getCount(): Int {
        return tvSeriesList.size
    }

    override fun getItem(position: Int): Any? {
        return tvSeriesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_obj_item, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        val movieData = tvSeriesList[position]
        viewHolder.name.text = movieData.name

        val posterUrl = "$IMAGE_STORAGE_URL/${movieData.poster_path}"



        Glide.with(context)
            .load(posterUrl)
            .placeholder(R.drawable.loading)
            .into(viewHolder.poster)







        convertView?.setOnClickListener {
            val clickedMovieId = movieData.id


            val intent = Intent(context, TvSeriseDetailsActivity::class.java)
            intent.putExtra("serise_id", clickedMovieId.toString())
            context.startActivity(intent)
        }







        return convertView!!
    }



    class ViewHolder(view: View) {
        val name: TextView = view.findViewById(R.id.movie_title)
        val poster : ImageView = view.findViewById(R.id.movie_poster)
    }
}