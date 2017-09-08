package com.nawin.androidmvparchitecture.taggedquestion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.auth.login.LoginActivity;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.model.TagItems;

import java.util.List;

/**
 * Created by nawin on 6/14/17.
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
        boolean isLoggedIn = Data.getInstance(this).isLoggedIn();
        if (!isLoggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
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
    public void showTagsLoadSuccess(List<TagItems> taggedQuestions, boolean hasMoreItems) {
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter) rvTaggedQuestion.getAdapter();
        if (adapter != null)
            adapter.detachLoadMore();
        adapter = new TaggedQuestionsAdapter(rvTaggedQuestion, taggedQuestions, presenter);

        if (hasMoreItems)
            adapter.attachLoadMore(presenter);
        this.rvTaggedQuestion.setAdapter(adapter);
    }

    @Override
    public void showTagsLoadError() {

    }

    @Override
    public void showEmptyTags() {

    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void showMoreTags(List<TagItems> items, boolean hasMoreItems) {
        ((TaggedQuestionsAdapter) this.rvTaggedQuestion.getAdapter()).addMoreItems(items, hasMoreItems);
    }

    @Override
    public void showLoadMoreError() {
        ((TaggedQuestionsAdapter) this.rvTaggedQuestion.getAdapter()).onLoadError();
    }

    @Override
    public void onLoadComplete() {
        ((TaggedQuestionsAdapter) this.rvTaggedQuestion.getAdapter()).onLoadComplete();

    }
}
