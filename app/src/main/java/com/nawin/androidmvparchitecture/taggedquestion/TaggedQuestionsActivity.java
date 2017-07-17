package com.nawin.androidmvparchitecture.taggedquestion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsActivity extends AppCompatActivity implements TaggedQuestionsContract.View {
    private TaggedQuestionsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagged_questions);
        presenter = new TaggedQuestionsPresenter(this, this);
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
    public void showTaggedQuestionLoadSuccess(List<TaggedQuestions> taggedQuestions, boolean hasMoreItems) {

    }

    @Override
    public void showTaggedQuestionLoadError() {

    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void showLoadMoreSuccess() {

    }

    @Override
    public void showLoadMoreError() {

    }
}
