package com.nawin.androidmvparchitecture.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nawin.androidmvparchitecture.R;

/**
 * Created by nawin on 7/12/17.
 */

public abstract class LoadMoreAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int DEFAULT_LOAD_MORE_THRESHOLD = 0;
    private static final int VIEW_TYPE_BOTTOM_PROGRESS = 0xDEADC0DE;
    private final LinearLayoutManager linearLayoutManager;
    private final RecyclerView recyclerView;
    private final LayoutInflater layoutInflater;
    private final ItemLoadPolicy loadPolicy;
    private final boolean showLoading;
    private RecyclerView.OnScrollListener scrollListener;
    private int loadMoreThreshold = DEFAULT_LOAD_MORE_THRESHOLD;

    public LoadMoreAdapter(@NonNull RecyclerView recyclerView) {
        this(recyclerView, true);
    }

    public LoadMoreAdapter(@NonNull RecyclerView recyclerView, boolean showLoading) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            throw new IllegalArgumentException("RecyclerView must have instance of LinearLayoutManager");
        }
        this.linearLayoutManager = (LinearLayoutManager) layoutManager;
        this.recyclerView = recyclerView;
        this.loadPolicy = new ItemLoadPolicy();
        this.showLoading = showLoading;
        this.layoutInflater = LayoutInflater.from(recyclerView.getContext());
    }

    public void attachLoadMore(final LoadMoreListener listener) {
        if (this.scrollListener != null) {
            throw new IllegalStateException("Listener already attached");
        }

        this.scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (listener != null && loadPolicy.canLoadMore()) {
                    if (linearLayoutManager.findLastVisibleItemPosition() >=
                            (getItemCount_() - (loadMoreThreshold + 1))) {
                        loadPolicy.setLoadStart();
                        listener.onLoadMore();
                        if (showLoading)
                            recyclerView.post(() -> notifyItemInserted(getItemCount_()));
                    }
                }
            }
        };
        this.recyclerView.addOnScrollListener(this.scrollListener);
    }

    public void detachLoadMore() {
        if (scrollListener != null)
            this.recyclerView.removeOnScrollListener(scrollListener);
    }

    public abstract int getItemCount_();

    public abstract VH onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType);

    public abstract void onBindViewHolder_(VH holder, int position);

    public int getItemViewType_(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_BOTTOM_PROGRESS) {
            return new BottomProgressViewHolder(layoutInflater.inflate(R.layout.layout_bottom_progress, parent, false));
        }
        return onCreateViewHolder_(layoutInflater, parent, viewType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!(showLoading && loadPolicy.isLoading && position == (getItemCount() - 1))) {
            onBindViewHolder_((VH) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return getItemCount_() + (showLoading && loadPolicy.isLoading ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoading && loadPolicy.isLoading && position == (getItemCount() - 1)) {
            return VIEW_TYPE_BOTTOM_PROGRESS;
        }
        return getItemViewType_(position);
    }

    public boolean hasItems() {
        return getItemCount_() > 0;
    }

    protected void onItemsAdded(int from, int size, boolean hasMoreItems) {
        if (hasMoreItems)
            loadPolicy.onLoad();
        else
            loadPolicy.onLoadComplete();

        if (showLoading)
            notifyItemChanged(getItemCount_() - size);

        notifyItemRangeChanged(from, size);

    }

    public void onLoadComplete() {
        loadPolicy.onLoadComplete();
        if (showLoading) {
            notifyItemRemoved(getItemCount_());
        }
    }

    public void onLoadError() {
        loadPolicy.onLoadFailed();
        if (showLoading)
            notifyItemRemoved(getItemCount_());
    }

    public void reset(boolean hasMoreItems) {
        loadPolicy.reset(hasMoreItems);
        notifyDataSetChanged();
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }

    private static class BottomProgressViewHolder extends RecyclerView.ViewHolder {

        BottomProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class ItemLoadPolicy {

        private static final int MAX_RETRY_POLICY_FAIL_COUNT = 3;
        private boolean loadComplete;
        private boolean isLoading;
        private int failCount = 0;

        private void setLoadStart() {
            this.isLoading = true;
        }

        private void onLoad() {
            this.isLoading = false;
            this.failCount = 0;
        }

        private void onLoadComplete() {
            this.isLoading = false;
            this.loadComplete = true;
            this.failCount = 0;
        }

        private void onLoadFailed() {
            this.isLoading = false;
            this.failCount++;
        }

        private boolean canLoadMore() {
            return !isLoading && !this.loadComplete && failCount <= MAX_RETRY_POLICY_FAIL_COUNT;
        }

        void reset(boolean hasMoreItems) {
            this.isLoading = false;
            this.loadComplete = !hasMoreItems;
            this.failCount = 0;
        }
    }
}
