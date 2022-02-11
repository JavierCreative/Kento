package com.creative.apps.kento.presentation.adapters

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsFragmentAdapter(private val mContext : Context) :
    FragmentStateAdapter(mContext as AppCompatActivity) {

    private val mDataset : MutableList<Fragment> = ArrayList<Fragment>()

    override fun getItemCount(): Int = mDataset.size

    override fun createFragment(position: Int): Fragment = mDataset[position]

    fun addFragment(fragment : Fragment) {
        mDataset.add(fragment)
    }
}