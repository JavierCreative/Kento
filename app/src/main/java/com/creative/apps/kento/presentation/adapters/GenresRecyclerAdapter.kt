package com.creative.apps.kento.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creative.apps.kento.databinding.ItemAnimeGenresBinding
import com.creative.apps.kento.domain.remote.genres.Genre

class GenresRecyclerAdapter(private val mContext : Context,
                        private var mDataset : List<Genre>?) :
    RecyclerView.Adapter<GenresRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnimeGenresBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataset?.get(position))
    }

    override fun getItemCount(): Int = mDataset!!.size

    fun setDataset(dataset : List<Genre>?) {
        mDataset = dataset
        notifyDataSetChanged()
    }

    class ViewHolder(private val mBinding : ItemAnimeGenresBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item : Genre?) {
            mBinding.genreMaterialtextview.text = item?.name
            val animes = "${item?.count} Animes"
            mBinding.animesMaterialtextview.text = animes
        }
    }
}