package com.nawin.androidmvparchitecture.taggedquestion;


import com.nawin.androidmvparchitecture.MvpComponent;

import io.reactivex.disposables.Disposable;

import static com.nawin.androidmvparchitecture.utils.Commons.dispose;
import static com.nawin.androidmvparchitecture.utils.Commons.isEmpty;

/**
 * Created by nawin on 6/14/17.
 */

class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
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
        disposable = component.data().requestTags().subscribe(response -> {
            if (response != null) {
                view.showTaggedQuestionLoadSuccess(response.get(0).getTags(), true);
            } else {
                view.showTaggedQuestionLoadError();
            }
        }, throwable -> view.showTaggedQuestionLoadError());
    }

    @Override
    public void stop() {
        dispose(disposable);
    }

    @Override
    public void onLoadMore() {
        view.showLoadMoreProgress();
        disposable = component.data().requestTags()
                .subscribe(response -> {
                    if (isEmpty(response))
                        view.showLoadMoreComplete();
                    else
                        view.showMoreItems();
                }, throwable -> view.showLoadMoreError(throwable.getMessage()));

    }

    @Override
    public void onTaggedQuestionSelected(String items) {

    }
}
