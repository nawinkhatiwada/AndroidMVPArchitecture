package com.nawin.androidmvparchitecture.taggedquestion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.nawin.androidmvparchitecture.BaseActivity;
import com.nawin.androidmvparchitecture.R;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsActivity extends BaseActivity implements TaggedQuestionsContract.View {
    private TaggedQuestionsContract.Presenter presenter;
    RecyclerView rvTaggedQuestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagged_questions);
        rvTaggedQuestion = (RecyclerView) findViewById(R.id.rvTaggedQuestions);
        presenter = new TaggedQuestionsPresenter(component, this);

        presenter.start();
    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public void onSessionTimeout(String message) {

    }

    @Override
    public void setPresenter(TaggedQuestionsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showTaggedQuestionLoadSuccess(List<String> taggedQuestions, boolean hasMoreItems) {

//    Remove Comment only if there is concept of loadMore in the app
        TaggedQuestionsAdapter
//                adapter = (TaggedQuestionsAdapter) rvTaggedQuestion.getAdapter();
//        if (adapter != null)
//            adapter.detachLoadMore();
        adapter = new TaggedQuestionsAdapter(rvTaggedQuestion, taggedQuestions, presenter);

//        if (hasMoreItems)
//            adapter.attachLoadMore(presenter);
        this.rvTaggedQuestion.setAdapter(adapter);
    }

    @Override
    public void showTaggedQuestionLoadError() {

    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void showMoreItems() {

    }

    @Override
    public void showLoadMoreComplete() {
        ((TaggedQuestionsAdapter) this.rvTaggedQuestion.getAdapter()).onLoadComplete();
    }

    @Override
    public void showLoadMoreError(String message) {

    }
}
