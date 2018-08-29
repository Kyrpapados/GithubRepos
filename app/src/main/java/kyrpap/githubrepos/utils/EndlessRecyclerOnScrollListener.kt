package kyrpap.githubrepos.utils

import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener {

    private var mPreviousTotal = 0
    private var mLoading = true
    private var mVisibleThreshold = 0
    var firstVisibleItem: Int = 0
        private set
    var visibleItemCount: Int = 0
        private set
    var totalItemCount: Int = 0
        private set

    private var mIsOrientationHelperVertical: Boolean = false
    private var mOrientationHelper: OrientationHelper? = null

    var currentPage = 1
        private set


    var layoutManager: RecyclerView.LayoutManager? = null
        private set

    constructor() {}

    constructor(layoutManager: RecyclerView.LayoutManager) {
        this.layoutManager = layoutManager
    }

    constructor(visibleThreshold: Int) {
        this.mVisibleThreshold = visibleThreshold
    }

    constructor(layoutManager: RecyclerView.LayoutManager, visibleThreshold: Int) {
        this.layoutManager = layoutManager
        this.mVisibleThreshold = visibleThreshold
    }

    private fun findFirstVisibleItemPosition(recyclerView: RecyclerView): Int {
        val child = findOneVisibleChild(0, layoutManager!!.childCount, false, true)
        return if (child == null) RecyclerView.NO_POSITION else recyclerView.getChildAdapterPosition(child)
    }

    private fun findLastVisibleItemPosition(recyclerView: RecyclerView): Int {
        val child = findOneVisibleChild(recyclerView.childCount - 1, -1, false, true)
        return if (child == null) RecyclerView.NO_POSITION else recyclerView.getChildAdapterPosition(child)
    }

    private fun findOneVisibleChild(fromIndex: Int, toIndex: Int, completelyVisible: Boolean,
                                    acceptPartiallyVisible: Boolean): View? {
        if (layoutManager!!.canScrollVertically() != mIsOrientationHelperVertical || mOrientationHelper == null) {
            mIsOrientationHelperVertical = layoutManager!!.canScrollVertically()
            mOrientationHelper = if (mIsOrientationHelperVertical)
                OrientationHelper.createVerticalHelper(layoutManager)
            else
                OrientationHelper.createHorizontalHelper(layoutManager)
        }

        val start = mOrientationHelper!!.startAfterPadding
        val end = mOrientationHelper!!.endAfterPadding
        val next = if (toIndex > fromIndex) 1 else -1
        var partiallyVisible: View? = null
        var i = fromIndex
        while (i != toIndex) {
            val child = layoutManager!!.getChildAt(i)
            if (child != null) {
                val childStart = mOrientationHelper!!.getDecoratedStart(child)
                val childEnd = mOrientationHelper!!.getDecoratedEnd(child)
                if (childStart < end && childEnd > start) {
                    if (completelyVisible) {
                        if (childStart >= start && childEnd <= end) {
                            return child
                        } else if (acceptPartiallyVisible && partiallyVisible == null) {
                            partiallyVisible = child
                        }
                    } else {
                        return child
                    }
                }
            }
            i += next
        }
        return partiallyVisible
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (layoutManager == null)
            layoutManager = recyclerView.layoutManager


        val footerCount = 1

        if (mVisibleThreshold == -1)
            mVisibleThreshold = findLastVisibleItemPosition(recyclerView) - findFirstVisibleItemPosition(recyclerView) - footerCount

        visibleItemCount = recyclerView.childCount - footerCount
        totalItemCount = layoutManager!!.itemCount - footerCount
        firstVisibleItem = findFirstVisibleItemPosition(recyclerView)

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
        }
        if (!mLoading && totalItemCount - visibleItemCount <= firstVisibleItem + mVisibleThreshold) {

            currentPage++

            onLoadMore(currentPage)

            mLoading = true
        }
    }

    @JvmOverloads
    fun resetPageCount(page: Int = 0) {
        mPreviousTotal = 0
        mLoading = true
        currentPage = page
    }

    abstract fun onLoadMore(currentPage: Int)
}