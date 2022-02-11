package com.creative.apps.kento.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.creative.apps.kento.presentation.adapters.SectionsFragmentAdapter
import com.creative.apps.kento.presentation.genres.GenresFragment
import com.creative.apps.kento.presentation.popular.PopularFragment
import com.creative.apps.kento.presentation.seasons.SeasonsFragment
import com.creative.apps.kento.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    @Inject lateinit var mSectionsAdapter : SectionsFragmentAdapter
    private lateinit var mBinding : ActivityMainBinding

    private val NAMES : List<String> = ArrayList(
        arrayListOf(
            "Popular",
            "Genres",
            "Seasons"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupUI()
    }

    private fun setupUI() {
        setupFragmentAdapter()
        setupTabs()
    }

    private fun setupFragmentAdapter() {
        mSectionsAdapter.addFragment(PopularFragment())
        mSectionsAdapter.addFragment(GenresFragment())
        mSectionsAdapter.addFragment(SeasonsFragment())
        mBinding.sectionsViewpager.adapter = mSectionsAdapter
    }

    private fun setupTabs() {
        TabLayoutMediator(mBinding.sectionsTablayout, mBinding.sectionsViewpager) { tab, position ->
            tab.text = NAMES[position]
        }.attach()
    }
}