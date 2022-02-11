package com.creative.apps.kento.presentation.genres

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creative.apps.kento.presentation.adapters.GenresRecyclerAdapter
import com.creative.apps.kento.databinding.FragmentGenresBinding
import com.creative.apps.kento.domain.remote.genres.Genre
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.framework.viewmodels.contracts.GenresContract
import com.creative.apps.kento.framework.viewmodels.GenresViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GenresFragment : Fragment(), GenresContract.GenresInterfaceView {

    @Inject lateinit var mGenresViewModel : GenresViewModel
    @Inject lateinit var mGenresAdapter : GenresRecyclerAdapter

    private lateinit var mBinding : FragmentGenresBinding
    private lateinit var mContext : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        mContext = requireContext()
        mBinding = FragmentGenresBinding.inflate(LayoutInflater.from(mContext), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
        setupRefresher()
        setupViewModel()
    }

    private fun setupViewModel() {
        mGenresViewModel.genresList.observe(viewLifecycleOwner){ genresList -> setAnimeGenres(genresList) }
        mGenresViewModel.error.observe(viewLifecycleOwner){ error -> onError(error) }
        showProgress()
        mGenresViewModel.getGenres()
    }

    private fun setupRefresher() {
        mBinding.refreshSwiperefreshlayout.setOnRefreshListener {
            mBinding.refreshSwiperefreshlayout.isRefreshing = false
            mGenresViewModel.getGenres()
        }
    }

    private fun setupRecyclerView() {
        mBinding.genresRecyclerview.setHasFixedSize(true)
        mBinding.genresRecyclerview.adapter = mGenresAdapter
    }

    override fun setAnimeGenres(genres: List<Genre>?) {
        mGenresAdapter.setDataset(genres)
        hideProgress()
    }

    override fun showProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.VISIBLE
    }

    override fun hideProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.GONE
    }

    override fun onError(error: ServiceError) {
        hideProgress()
        val alert = AlertDialog.Builder(mContext)
            .setMessage(error.message)
            .setPositiveButton("Accept"){ dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alert.show()
    }
}