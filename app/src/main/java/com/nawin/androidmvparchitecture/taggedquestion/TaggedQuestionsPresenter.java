package com.nawin.androidmvparchitecture.taggedquestion;


import android.util.Log;

import com.nawin.androidmvparchitecture.MvpComponent;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;
import static com.nawin.androidmvparchitecture.utils.Commons.dispose;
import static com.nawin.androidmvparchitecture.utils.Commons.isEmpty;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
    private final MvpComponent component;
    private TaggedQuestionsContract.View view;
    private Disposable disposable;
    private int offset;

    public TaggedQuestionsPresenter(MvpComponent component, TaggedQuestionsContract.View view) {
        this.component = component;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        this.offset = 1;
        disposable = component.data().requestTags().subscribe(response -> {
            if (response != null) {
                Tags tags = response.get(0);
                view.showTaggedQuestionLoadSuccess(tags.getTags(), true);
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
                        view.showLoadMoreError(component.context().getString(R.string.server_error));
                }, throwable -> view.showLoadMoreError(throwable.getMessage()));

    }

    @Override
    public void onTaggedQuestionSelected(String items) {

    }
}
