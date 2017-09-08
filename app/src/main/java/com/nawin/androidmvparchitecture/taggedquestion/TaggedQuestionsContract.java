package com.nawin.androidmvparchitecture.taggedquestion;

import com.nawin.androidmvparchitecture.BasePresenter;
import com.nawin.androidmvparchitecture.BaseView;
import com.nawin.androidmvparchitecture.data.model.TagItems;
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter;

import java.util.List;

/**
 * Created by nawin on 6/14/17.
 */

public interface TaggedQuestionsContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showTagsLoadSuccess(List<TagItems> items, boolean hasMoreItems);

        void showTagsLoadError();

        void showEmptyTags();

        void showLoadMoreProgress();

        void showMoreTags(List<TagItems> items, boolean hasMoreItems);

        void showLoadMoreError();

        void onLoadComplete();
    }

    interface Presenter extends BasePresenter,
            LoadMoreAdapter.LoadMoreListener,
            TaggedQuestionsAdapter.TaggedQuestionSelectionListener {

    }
}
