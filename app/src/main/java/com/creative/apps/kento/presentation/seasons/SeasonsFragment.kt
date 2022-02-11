package com.creative.apps.kento.presentation.seasons

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creative.apps.kento.presentation.adapters.SeasonsYearsRecyclerView
import com.creative.apps.kento.databinding.FragmentSeasonsBinding
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.framework.viewmodels.contracts.SeasonsContract
import com.creative.apps.kento.framework.viewmodels.SeasonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeasonsFragment : Fragment(), SeasonsContract.SeasonsInterfaceView{

    @Inject lateinit var mSeasonsViewModel : SeasonsViewModel
    @Inject lateinit var mSeasonsAdapter : SeasonsYearsRecyclerView

    private lateinit var mBinding : FragmentSeasonsBinding
    private lateinit var mContext : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mContext = requireContext()
        mBinding = FragmentSeasonsBinding.inflate(LayoutInflater.from(mContext), container, false)
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
        mSeasonsViewModel.seasonsList.observe(viewLifecycleOwner){ seasonsList -> setSeasons(seasonsList) }
        mSeasonsViewModel.error.observe(viewLifecycleOwner){ error -> onError(error)}
        showProgress()
        mSeasonsViewModel.getSeasons()
    }

    private fun setupRefresher() {
        mBinding.refreshSwiperefreshlayout.setOnRefreshListener {
            mBinding.refreshSwiperefreshlayout.isRefreshing = false
            mSeasonsViewModel.getSeasons()
        }
    }

    private fun setupRecyclerView() {
        mBinding.seasonsRecyclerview.setHasFixedSize(true)
        mBinding.seasonsRecyclerview.adapter = mSeasonsAdapter
    }

    override fun setSeasons(seasons: List<Int>?) {
        mSeasonsAdapter.setDataset(seasons)
        hideProgress()
    }

    override fun showProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.VISIBLE
    }

    override fun hideProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.GONE
    }

    override fun onError(error : ServiceError) {
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