package com.example.pagingrecyclerview

import android.text.BoringLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val linear : LinearLayoutManager) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        val visibleItemCount = linear.childCount
        val totalItemCount = linear.itemCount
        val fistVisibleItemCount = linear.findFirstVisibleItemPosition()

        if (isLastPage())
        {
            return
        }

        if (fistVisibleItemCount >=0 && (visibleItemCount + fistVisibleItemCount) >= totalItemCount)
        {
            loadMoreItem()
        }

    }


    abstract fun loadMoreItem()
    abstract fun isLoading():Boolean
    abstract fun isLastPage():Boolean

}