package com.nawin.androidmvparchitecture.taggedquestion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nawin.androidmvparchitecture.BaseActivity;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.auth.login.LoginActivity;
import com.nawin.androidmvparchitecture.data.model.TagItems;
import com.nawin.androidmvparchitecture.databinding.ActivityTaggedQuestionsBinding;
import com.nawin.androidmvparchitecture.utils.Commons;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nawin on 6/14/17.
 */

public class TaggedQuestionsActivity extends BaseActivity implements TaggedQuestionsContract.View {

    @Inject
    TaggedQuestionsContract.Presenter presenter;
    private ActivityTaggedQuestionsBinding binding;
    private ProgressDialog progressDialog;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, TaggedQuestionsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tagged_questions);
        boolean isLoggedIn = data.isLoggedIn();
        if (!isLoggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter) binding.rvTaggedQuestion.getAdapter();
        if (adapter != null)
            adapter.detachLoadMore();
        adapter = new TaggedQuestionsAdapter(binding.rvTaggedQuestion, taggedQuestions, presenter);

        if (hasMoreItems)
            adapter.attachLoadMore(presenter);
        binding.rvTaggedQuestion.setAdapter(adapter);
    }

    @Override
    public void showTagsLoadError(String message) {
        dismissDialog();
        Toast.makeText(TaggedQuestionsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyTags(String message) {
        dismissDialog();
        Toast.makeText(TaggedQuestionsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void showMoreTags(List<TagItems> items, boolean hasMoreItems) {
        ((TaggedQuestionsAdapter) binding.rvTaggedQuestion.getAdapter()).addMoreItems(items, hasMoreItems);
    }

    @Override
    public void showLoadMoreError() {
        ((TaggedQuestionsAdapter) binding.rvTaggedQuestion.getAdapter()).onLoadError();
    }

    @Override
    public void onLoadComplete() {
        ((TaggedQuestionsAdapter) binding.rvTaggedQuestion.getAdapter()).onLoadComplete();

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
        Toast.makeText(TaggedQuestionsActivity.this, getString(R.string.network_not_available_error), Toast.LENGTH_SHORT).show();

    }

    private void dismissDialog() {
        if (progressDialog!= null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
