package com.creative.apps.kento.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creative.apps.kento.R
import com.creative.apps.kento.databinding.ItemCharactersBinding
import com.creative.apps.kento.domain.remote.characters.Character
import com.creative.apps.kento.presentation.adapters.listeners.OnCharacterSelectedListener
import java.util.ArrayList

class CharactersRecyclerAdapter(private val mContext : Context,
                                private var mDataset : MutableList<Character>?) :
RecyclerView.Adapter<CharactersRecyclerAdapter.ViewHolder>() {

    private lateinit var mListener : OnCharacterSelectedListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binder = ItemCharactersBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataset?.get(position), mContext, mListener)
    }

    override fun getItemCount(): Int = mDataset!!.size

    fun setDataset(dataset : List<Character>?){
        val list = ArrayList(dataset!!)
        mDataset?.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener : OnCharacterSelectedListener) {
        mListener = listener
    }

    class ViewHolder(private val mBinding : ItemCharactersBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item : Character?, context: Context, listener: OnCharacterSelectedListener){

            mBinding.bannerShapeableimageview.setOnClickListener { listener.onCharacterSelected(item?.id!!) }
            mBinding.animeTitleMaterialtextview.text = item?.name
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