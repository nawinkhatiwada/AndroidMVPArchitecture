package com.nawin.androidmvparchitecture.taggedquestion;


import com.nawin.androidmvparchitecture.MvpComponent;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.error.FailedResponseException;
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException;
import com.nawin.androidmvparchitecture.data.model.TagItems;

import java.util.List;

import io.reactivex.disposables.Disposable;

import static com.nawin.androidmvparchitecture.utils.Commons.dispose;
import static com.nawin.androidmvparchitecture.utils.Commons.isEmpty;

/**
 * Created by nawin on 6/14/17.
 */

class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
    private static final int LIMIT = 10;
    private final MvpComponent component;
    private TaggedQuestionsContract.View view;
    private Disposable disposable;
    private int offset;

    TaggedQuestionsPresenter(MvpComponent component, TaggedQuestionsContract.View view) {
        this.component = component;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.showProgress();
        this.offset = 1;
        disposable = component.data().requestTags(offset, LIMIT).subscribe(response -> {
            if (response != null) {
                int itemCount = response.getItemCount();
                List<TagItems> items = response.getItems();
                if (itemCount > 0 && !isEmpty(items)) {
                    final int count = items.size();
                    offset += count;
                    view.showTagsLoadSuccess(items, itemCount > offset);
                } else {
                    view.showEmptyTags(component.context().getString(R.string.data_not_available));
                }
            } else {
                view.showEmptyTags(component.context().getString(R.string.server_error));
            }
        }, throwable -> {
            if (throwable instanceof FailedResponseException) {
                view.showTagsLoadError(throwable.getMessage());
            } else if (throwable instanceof NetworkNotAvailableException) {
                view.showNetworkNotAvailableError();
            } else {
                view.showTagsLoadError(component.context().getString(R.string.server_error));
            }
        });
    }

    @Override
    public void stop() {
        dispose(disposable);
    }

    @Override
    public void onLoadMore() {
        view.showLoadMoreProgress();
        disposable = component.data().requestTags(offset, LIMIT)
                .subscribe(response -> {
                    if (response != null) {
                        int itemCount = response.getItemCount();
                        List<TagItems> items = response.getItems();
                        if (itemCount > 0 && !isEmpty(items)) {
                            final int count = items.size();
                            offset += count;
                            view.showMoreTags(items, itemCount > offset);
                        } else {
                            view.onLoadComplete();
                        }
                    } else {
                        view.showLoadMoreError();
                    }
                }, throwable -> view.showLoadMoreError());

    }

    @Override
    public void onLogout() {
        component.data().logout();
    }

    @Override
    public void onTaggedQuestionSelected(List<TagItems> items) {
    }
}
