package com.nawin.androidmvparchitecture.taggedquestion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nawin.androidmvparchitecture.BaseActivity;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.databinding.ActivityTaggedQuestionsBinding;

import java.util.List;

/**
 * Created by nawin on 6/14/17.
 */

public class TaggedQuestionsActivity extends BaseActivity implements TaggedQuestionsContract.View {
    private TaggedQuestionsContract.Presenter presenter;
    ActivityTaggedQuestionsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tagged_questions);
        presenter = new TaggedQuestionsPresenter(component, this);
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
    public void showProgress() {

    }

    @Override
    public void showTaggedQuestionLoadSuccess(List<String> taggedQuestions, boolean hasMoreItems) {

//    Remove Comment only if there is concept of loadMore in the app
        TaggedQuestionsAdapter
//                adapter = (TaggedQuestionsAdapter) rvTaggedQuestion.getAdapter();
//        if (adapter != null)
//            adapter.detachLoadMore();
        adapter = new TaggedQuestionsAdapter(binding.rvTaggedQuestions, taggedQuestions, presenter);

//        if (hasMoreItems)
//            adapter.attachLoadMore(presenter);
        this.binding.rvTaggedQuestions.setAdapter(adapter);
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
        ((TaggedQuestionsAdapter) this.binding.rvTaggedQuestions.getAdapter()).onLoadComplete();
    }

    @Override
    public void showLoadMoreError(String message) {

    }

    @Override
    public void setPresenter(TaggedQuestionsContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
