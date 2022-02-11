package com.creative.apps.kento.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creative.apps.kento.databinding.ItemSeasonBinding

class SeasonsYearsRecyclerView(private val mContext : Context,
                               private var mDataset : List<Int>?) :
    RecyclerView.Adapter<SeasonsYearsRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemSeasonBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataset?.get(position))
    }

    override fun getItemCount(): Int = mDataset!!.size

    fun setDataset(dataset : List<Int>?) {
        mDataset = dataset
        notifyDataSetChanged()
    }

    class ViewHolder(private val mBinding : ItemSeasonBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item : Int?) {
            mBinding.yearMaterialtextview.text = item?.toString()
        }
    }
}