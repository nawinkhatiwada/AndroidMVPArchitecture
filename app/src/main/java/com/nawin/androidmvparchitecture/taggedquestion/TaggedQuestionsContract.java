package com.nawin.androidmvparchitecture.taggedquestion;

import com.nawin.androidmvparchitecture.BasePresenter;
import com.nawin.androidmvparchitecture.BaseView;
import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public interface TaggedQuestionsContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void showTaggedQuestionLoadSuccess(List<String> taggedQuestions, boolean hasMoreItems);

        void showTaggedQuestionLoadError();

        void showLoadMoreProgress();

        void showMoreItems();

        void showLoadMoreComplete();

        void showLoadMoreError(String message);


    }

    interface Presenter extends BasePresenter,
            LoadMoreAdapter.LoadMoreListener,
            TaggedQuestionsAdapter.TaggedQuestionSelectionListener {

    }
}
