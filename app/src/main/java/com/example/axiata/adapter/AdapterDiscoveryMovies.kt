package com.example.axiata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.axiata.R
import com.example.axiata.entity.ResultsItem
import kotlinx.android.synthetic.main.items_discovery_movies.view.*

class AdapterDiscoveryMovies : RecyclerView.Adapter<AdapterDiscoveryMovies.VH>() {
    private val list = arrayListOf<ResultsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDiscoveryMovies.VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_discovery_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterDiscoveryMovies.VH, position: Int) {
        val items = list[position]
        with(holder.itemView) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w185${items.posterPath}")
                .into(item_img_movie)
            item_judul_movie.text = items.originalTitle
            item_popularity_movie.text = "${items.popularity}"
            item_overview_movie.text = items.overview
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view)

    internal fun addList(mList: ArrayList<ResultsItem>) {
        list.addAll(mList)
        if (list.size > 0) {
            notifyItemInserted(list.size)
        } else {
            notifyDataSetChanged()
        }
    }

    internal fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }
}