package com.creative.apps.kento.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.creative.apps.kento.R
import com.creative.apps.kento.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
        findNavController(R.id.host_detail_container_fragment)
            .setGraph(R.navigation.detail_navigation, DetailAnimeFragment.getBundleArgs(intent.getIntExtra(
                ANIME_ID_KEY, 0)))
        setupActionBarWithNavController(findNavController(R.id.host_detail_container_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.host_detail_container_fragment)
        return navController.popBackStack() || super.onSupportNavigateUp()
    }

    companion object {
        const val ANIME_ID_KEY = "com.creative.apps.kento.presentation.detail.DetailActivity.AnimeIdKey"
        fun getIntent(id : Int, context : Context) : Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ANIME_ID_KEY, id)
            return intent
        }
    }
}