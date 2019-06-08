package com.nawin.androidmvparchitecture.taggedquestion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nawin.androidmvparchitecture.BaseFragment;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.auth.login.LoginActivity;
import com.nawin.androidmvparchitecture.data.model.TagItems;
import com.nawin.androidmvparchitecture.databinding.FragmentTaggedQuestionsBinding;
import com.nawin.androidmvparchitecture.utils.Commons;

import java.util.List;

import javax.inject.Inject;

public class TaggedQuestionsFragment extends BaseFragment implements TaggedQuestionsContract.View {

    public static TaggedQuestionsFragment getInstance(){
        TaggedQuestionsFragment taggedQuestionsFragment = new TaggedQuestionsFragment();
//        Bundle bundle = new Bundle();
//        bundle.putFloat("a",1);
//        taggedQuestionsFragment.setArguments(bundle);
        return taggedQuestionsFragment;
    }
    @Inject
    TaggedQuestionsContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private FragmentTaggedQuestionsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tagged_questions,null,false);

        boolean isLoggedIn = data.isLoggedIn();
        if (!isLoggedIn) {
            Activity activity = getActivity();
            if (activity != null) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        presenter.start();
        super.onResume();
    }


    @Override
    public void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public void showProgress() {
        progressDialog = Commons.showLoadingDialog(requireContext());
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
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyTags(String message) {
        dismissDialog();
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadMoreProgress() {

    }

    @Override
    public void showMoreTags(List<TagItems> items, boolean hasMoreItems) {
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter)binding.rvTaggedQuestion.getAdapter();
        if (adapter != null) {
            adapter.addMoreItems(items, hasMoreItems);
        }
    }

    @Override
    public void showLoadMoreError() {
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter)binding.rvTaggedQuestion.getAdapter();
        if (adapter != null){
            adapter.onLoadError();
        }
    }

    @Override
    public void onLoadComplete() {
        TaggedQuestionsAdapter adapter = (TaggedQuestionsAdapter)binding.rvTaggedQuestion.getAdapter();
        if (adapter != null){
            adapter.onLoadComplete();
        }
    }

    @Override
    public void onLogoutSelection() {
        presenter.onLogout();

        Activity activity = getActivity();
        if (activity != null) {
            startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }
    }

    @Override
    public void showNetworkNotAvailableError() {
        dismissDialog();
        Toast.makeText(requireContext(), getString(R.string.network_not_available_error), Toast.LENGTH_SHORT).show();

    }

    private void dismissDialog() {
        if (progressDialog!= null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_items, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

}
