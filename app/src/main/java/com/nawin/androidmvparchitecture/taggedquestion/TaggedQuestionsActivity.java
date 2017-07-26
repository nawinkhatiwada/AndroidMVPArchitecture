package com.nawin.androidmvparchitecture.taggedquestion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsActivity extends AppCompatActivity implements TaggedQuestionsContract.View {
    private TaggedQuestionsContract.Presenter presenter;
    RecyclerView rvTaggedQuestion;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, TaggedQuestionsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagged_questions);
        rvTaggedQuestion = (RecyclerView) findViewById(R.id.rvTaggedQuestions);
        presenter = new TaggedQuestionsPresenter(this, this);

        presenter.start();
    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();
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
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter) rvTaggedQuestion.getAdapter();
        if (adapter != null)
            adapter.detachLoadMore();
        adapter = new TaggedQuestionsAdapter(rvTaggedQuestion, taggedQuestions, presenter);

        if (hasMoreItems)
            adapter.attachLoadMore(presenter);
        this.rvTaggedQuestion.setAdapter(adapter);
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
