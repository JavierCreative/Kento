package com.creative.apps.kento.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creative.apps.kento.R
import com.creative.apps.kento.databinding.ItemAnimesBinding
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.presentation.adapters.listeners.OnAnimeSelectedListener
import java.util.ArrayList

class AnimesRecyclerAdapter(private val mContext : Context,
                            private var mDataset : MutableList<Anime>?) :
    RecyclerView.Adapter<AnimesRecyclerAdapter.ViewHolder>() {

    private lateinit var mListener: OnAnimeSelectedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnimesBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataset?.get(position), mContext, mListener)
    }

    override fun getItemCount(): Int = mDataset!!.size

    fun setDataset(dataset : List<Anime>?){
        val list = ArrayList(dataset!!)
        if (mDataset?.size == 0)
        {
            mDataset?.addAll(list)
            notifyDataSetChanged()
        } else {
            val oldLastIndex = mDataset!!.size
            mDataset?.addAll(list)
            notifyItemRangeInserted(oldLastIndex, (mDataset!!.size - 1))
        }
    }

    fun setListener(listener : OnAnimeSelectedListener) {
        mListener = listener
    }

    fun clearAdapter() {
        mDataset?.clear()
    }

    class ViewHolder(private val mBinding : ItemAnimesBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item : Anime?, context: Context, listener: OnAnimeSelectedListener){
            mBinding.bannerShapeableimageview.setOnClickListener { listener.onAnimeClicked(item?.id!!) }
            mBinding.animeTitleMaterialtextview.text = item?.title
            mBinding.animeVideosMaterialtextview.text = item?.status
            item?.episodes?.let {
                val episodes = "$it videos"
                mBinding.animeVideosMaterialtextview.text = episodes
            }
            val radius = context.resources.getDimension(R.dimen.corner_radius)
            val shapeAppearanceModel = mBinding.bannerShapeableimageview.shapeAppearanceModel.toBuilder()
                .setAllCornerSizes(radius)
                .build()

            mBinding.bannerShapeableimageview.shapeAppearanceModel = shapeAppearanceModel
            Glide.with(context)
                .load(item?.images?.jpg?.url)
                .centerCrop()
                .into(mBinding.bannerShapeableimageview)
        }
    }
}