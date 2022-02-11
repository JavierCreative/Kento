package com.creative.apps.kento.presentation.adapters.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class AnimesPaginatorScrollListener(private val mGridManager : GridLayoutManager) : RecyclerView.OnScrollListener() {

    companion object {
        const val INITIAL_PAGE : Int = 1
        const val MAX_PAGE_SIZE : Int = 25
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = mGridManager.childCount
        val totalItemCount = mGridManager.itemCount
        val firstVisibleItem = mGridManager.findFirstCompletelyVisibleItemPosition()
        val isLoading = isLoading()
        val isLast = isLastPage()
        if ((!isLoading && !isLast) && ((visibleItemCount + firstVisibleItem) >= totalItemCount &&
                    firstVisibleItem > 0 && totalItemCount >= MAX_PAGE_SIZE)) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLoading() : Boolean
    abstract fun isLastPage() : Boolean
}