package com.nawin.androidmvparchitecture.taggedquestion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.auth.login.LoginActivity;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.model.TagItems;
import com.nawin.androidmvparchitecture.utils.Commons;

import java.util.List;

/**
 * Created by nawin on 6/14/17.
 */

public class TaggedQuestionsActivity extends AppCompatActivity implements TaggedQuestionsContract.View {
    private TaggedQuestionsContract.Presenter presenter;
    private RecyclerView rvTaggedQuestion;
    private ProgressDialog progressDialog;

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
        presenter = new TaggedQuestionsPresenter(this);
        presenter.start();
    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                onLogoutSelection();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressDialog = Commons.showLoadingDialog(this);
    }

    @Override
    public void showTagsLoadSuccess(List<TagItems> taggedQuestions, boolean hasMoreItems) {
        dismissDialog();
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter) rvTaggedQuestion.getAdapter();
        if (adapter != null)
            adapter.detachLoadMore();
        adapter = new TaggedQuestionsAdapter(rvTaggedQuestion, taggedQuestions, presenter);

        if (hasMoreItems)
            adapter.attachLoadMore(presenter);
        this.rvTaggedQuestion.setAdapter(adapter);
    }

    @Override
    public void showTagsLoadError(@StringRes int message) {
        dismissDialog();
        Toast.makeText(TaggedQuestionsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyTags(@StringRes int message) {
        dismissDialog();
        Toast.makeText(TaggedQuestionsActivity.this, message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onLogoutSelection() {
        presenter.onLogout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void showNetworkNotAvailableError() {
        dismissDialog();
        Toast.makeText(this, getString(R.string.network_not_available_error), Toast.LENGTH_SHORT).show();

    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
