package com.nawin.androidmvparchitecture.taggedquestion;

import com.nawin.androidmvparchitecture.BasePresenter;
import com.nawin.androidmvparchitecture.BaseView;
import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public interface TaggedQuestionsContract {

    interface View extends BaseView<Presenter> {
        void showProgress();
        void showTaggedQuestionLoadSuccess(List<TaggedQuestions> taggedQuestions, boolean hasMoreItems);
        void showTaggedQuestionLoadError();
        void showLoadMoreProgress();
        void showLoadMoreSuccess();
        void showLoadMoreError();

    }

    interface Presenter extends BasePresenter {
        
    }
}
