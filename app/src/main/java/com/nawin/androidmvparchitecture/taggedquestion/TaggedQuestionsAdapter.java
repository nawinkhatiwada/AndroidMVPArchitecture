package com.nawin.androidmvparchitecture.taggedquestion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsAdapter extends LoadMoreAdapter<TaggedQuestionsAdapter.TaggedQuestionsHolder> {


    public TaggedQuestionsAdapter(@NonNull RecyclerView recyclerView, boolean showLoading, List<TaggedQuestions> taggedQuestions) {
        super(recyclerView, showLoading);
    }

    @Override
    public int getItemCount_() {
        return 0;
    }

    @Override
    public TaggedQuestionsHolder onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder_(TaggedQuestionsHolder holder, int position) {

    }

    @Override
    public int getItemViewType_(int position) {
        return 0;
    }

    public static class TaggedQuestionsHolder extends RecyclerView.ViewHolder{
        public TaggedQuestionsHolder(View itemView) {
            super(itemView);
        }
    }
}
