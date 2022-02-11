package com.creative.apps.kento.presentation.popular

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.creative.apps.kento.presentation.adapters.AnimesRecyclerAdapter
import com.creative.apps.kento.presentation.adapters.pagination.AnimesPaginatorScrollListener
import com.creative.apps.kento.databinding.FragmentPopularBinding
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.framework.viewmodels.PopularAnimesViewModel
import com.creative.apps.kento.framework.viewmodels.contracts.PopularAnimesContract
import com.creative.apps.kento.presentation.adapters.listeners.OnAnimeSelectedListener
import com.creative.apps.kento.presentation.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : Fragment(), PopularAnimesContract.PopularAnimesInterfaceView, OnAnimeSelectedListener{

    @Inject lateinit var mAnimesAdapter : AnimesRecyclerAdapter
    @Inject lateinit var mPopularAnimesViewModel : PopularAnimesViewModel

    private lateinit var mBinding : FragmentPopularBinding
    private lateinit var mContext : Context

    private var mCurrentPage : Int = AnimesPaginatorScrollListener.INITIAL_PAGE
    private var mIsLastPage : Boolean = false
    private var mTotalPage : Int = 10
    private var mIsLoading : Boolean = false
    private var mItemCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        mContext = requireContext()
        mBinding = FragmentPopularBinding.inflate(LayoutInflater.from(mContext), container, false)
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
        mPopularAnimesViewModel.popularAnimeList.observe(viewLifecycleOwner) { animeList -> setAnimes(animeList) }
        mPopularAnimesViewModel.error.observe(viewLifecycleOwner) { error -> onError(error) }
        showProgress()
        mPopularAnimesViewModel.getAnimes(mCurrentPage)
    }

    private fun setupRefresher() {
        mBinding.refreshSwiperefreshlayout.setOnRefreshListener {
            mBinding.refreshSwiperefreshlayout.isRefreshing = false
            mItemCount = 0;
            mCurrentPage = AnimesPaginatorScrollListener.INITIAL_PAGE;
            mIsLastPage = false;
            mAnimesAdapter.clearAdapter()
            mPopularAnimesViewModel.getAnimes(mCurrentPage)
            showProgress()
        }
    }

    private fun setupRecyclerView() {
        val manager = GridLayoutManager(mContext, 2)
        mBinding.animesRecyclerview.setHasFixedSize(true)
        mBinding.animesRecyclerview.layoutManager = manager
        mAnimesAdapter.setListener(this)
        mBinding.animesRecyclerview.adapter = mAnimesAdapter
        mBinding.animesRecyclerview.addOnScrollListener(object : AnimesPaginatorScrollListener(manager) {

            override fun isLastPage(): Boolean = mCurrentPage >= mTotalPage

            override fun isLoading(): Boolean = mIsLoading

            override fun loadMoreItems() {
                mIsLoading = true
                mCurrentPage++
                mPopularAnimesViewModel.getAnimes(mCurrentPage)
                showProgress()
            }

        })
    }

    override fun setAnimes(animes: List<Anime>?) {
        mAnimesAdapter.setDataset(animes)
        hideProgress()
    }

    override fun showProgress() {
        mBinding.progressLinearprogressindicator.visibility = ViewGroup.VISIBLE
    }

    override fun hideProgress() {
        mIsLoading = false
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

    override fun onAnimeClicked(id: Int) {
        val detail = DetailActivity.getIntent(id, mContext)
        startActivity(detail)
    }
}