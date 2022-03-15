package com.mories.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mories.test.databinding.ItemsDiscoveryMoviesBinding
import com.mories.test.entity.ResultsItem

class AdapterDiscoveryMovies : RecyclerView.Adapter<AdapterDiscoveryMovies.VH>() {
    private val list = arrayListOf<ResultsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VH(
            ItemsDiscoveryMoviesBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val items = list[position]
        holder.bind(items)
    }

    inner class VH(private val binding: ItemsDiscoveryMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsItem: ResultsItem) {
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w185${resultsItem.posterPath}")
                .into(binding.itemImgMovie)
            binding.itemJudulMovie.text = resultsItem.originalTitle
            binding.itemPopularityMovie.text = "${resultsItem.popularity}"
            binding.itemOverviewMovie.text = resultsItem.overview
        }
    }

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