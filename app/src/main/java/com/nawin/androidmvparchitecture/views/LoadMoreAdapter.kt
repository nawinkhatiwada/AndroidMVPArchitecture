package com.nawin.androidmvparchitecture.views

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nawin.androidmvparchitecture.R

/**
 * Created on 11/30/17.
 */
abstract class LoadMoreAdapter<VH : RecyclerView.ViewHolder>
@JvmOverloads
constructor(private val recyclerView: RecyclerView,
            private val showLoading: Boolean = true) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val linearLayoutManager: LinearLayoutManager
    private val layoutInflater: LayoutInflater
    private val loadPolicy: ItemLoadPolicy
    private var scrollListener: RecyclerView.OnScrollListener? = null
    private val loadMoreThreshold = DEFAULT_LOAD_MORE_THRESHOLD

    abstract val itemCount_: Int

    init {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager == null || layoutManager !is LinearLayoutManager) {
            throw IllegalArgumentException("RecyclerView must have instance of LinearLayoutManager")
        }
        this.linearLayoutManager = layoutManager
        this.loadPolicy = ItemLoadPolicy()
        this.layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    fun attachLoadMore(listener: LoadMoreListener?) {
        if (this.scrollListener != null) {
            throw IllegalStateException("Listener already attached")
        }

        this.scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (listener != null && loadPolicy.canLoadMore()) {
                    if (linearLayoutManager.findLastVisibleItemPosition() >= itemCount_ - (loadMoreThreshold + 1)) {
                        loadPolicy.setLoadStart()
                        listener.onLoadMore()
                        if (showLoading)
                            recyclerView!!.post { notifyItemInserted(itemCount_) }
                    }
                }
            }
        }
        this.recyclerView.addOnScrollListener(this.scrollListener)
    }

    fun detachLoadMore() {
        if (scrollListener != null)
            this.recyclerView.removeOnScrollListener(scrollListener)
    }

    abstract fun onCreateViewHolder_(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VH

    abstract fun onBindViewHolder_(holder: VH, position: Int)

    fun getItemViewType_(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_BOTTOM_PROGRESS) {
            BottomProgressViewHolder(layoutInflater.inflate(R.layout.layout_bottom_progress, parent, false))
        } else onCreateViewHolder_(layoutInflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!(showLoading && loadPolicy.isLoading && position == itemCount - 1)) {
            onBindViewHolder_(holder as VH, position)
        }
    }

    override fun getItemCount(): Int {
        return itemCount_ + if (showLoading && loadPolicy.isLoading) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (showLoading && loadPolicy.isLoading && position == itemCount - 1) {
            VIEW_TYPE_BOTTOM_PROGRESS
        } else getItemViewType_(position)
    }

    fun hasItems(): Boolean {
        return itemCount_ > 0
    }

    protected fun onItemsAdded(from: Int, size: Int, hasMoreItems: Boolean) {
        if (hasMoreItems)
            loadPolicy.onLoad()
        else
            loadPolicy.onLoadComplete()

        if (showLoading)
            notifyItemChanged(itemCount_ - size)

        notifyItemRangeChanged(from, size)

    }

    fun onLoadComplete() {
        loadPolicy.onLoadComplete()
        if (showLoading) {
            notifyItemRemoved(itemCount_)
        }
    }

    fun onLoadError() {
        loadPolicy.onLoadFailed()
        if (showLoading)
            notifyItemRemoved(itemCount_)
    }

    fun reset(hasMoreItems: Boolean) {
        loadPolicy.reset(hasMoreItems)
        notifyDataSetChanged()
    }

    interface LoadMoreListener {
        fun onLoadMore()
    }

    private class BottomProgressViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)


    private class ItemLoadPolicy {
        internal var loadComplete: Boolean = false
        internal var isLoading: Boolean = false
        internal var failCount = 0

        internal fun setLoadStart() {
            this.isLoading = true
        }

        internal fun onLoad() {
            this.isLoading = false
            this.failCount = 0
        }

        internal fun onLoadComplete() {
            this.isLoading = false
            this.loadComplete = true
            this.failCount = 0
        }

        internal fun onLoadFailed() {
            this.isLoading = false
            this.failCount++
        }

        internal fun canLoadMore(): Boolean {
            return !isLoading && !this.loadComplete && failCount <= MAX_RETRY_POLICY_FAIL_COUNT
        }

        internal fun reset(hasMoreItems: Boolean) {
            this.isLoading = false
            this.loadComplete = !hasMoreItems
            this.failCount = 0
        }

        companion object {

            private val MAX_RETRY_POLICY_FAIL_COUNT = 3
        }
    }

    companion object {

        private val DEFAULT_LOAD_MORE_THRESHOLD = 0
        private val VIEW_TYPE_BOTTOM_PROGRESS = -0x21523f22
    }
}
