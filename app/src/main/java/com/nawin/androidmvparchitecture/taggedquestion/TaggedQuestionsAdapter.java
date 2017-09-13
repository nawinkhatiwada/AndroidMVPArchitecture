package com.nawin.androidmvparchitecture.taggedquestion;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.model.TagItems;
import com.nawin.androidmvparchitecture.databinding.ViewHolderTaggedQuestionItemsBinding;
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter;

import java.util.List;

/**
 * Created by nawin on 6/14/17.
 */

class TaggedQuestionsAdapter extends LoadMoreAdapter<TaggedQuestionsAdapter.TaggedQuestionsHolder> {
    private final List<TagItems> items;
    private final TaggedQuestionSelectionListener listener;

    TaggedQuestionsAdapter(@NonNull RecyclerView recyclerView,
                           List<TagItems> taggedQuestions,
                           TaggedQuestionSelectionListener listener) {
        super(recyclerView);
        this.items = taggedQuestions;
        this.listener = listener;
    }

    @Override
    public int getItemCount_() {
        return items.size();
    }

    @Override
    public TaggedQuestionsHolder onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new TaggedQuestionsHolder(DataBindingUtil.inflate(inflater, R.layout.view_holder_tagged_question_items, parent, false));
    }

    @Override
    public void onBindViewHolder_(final TaggedQuestionsHolder holder, final int position) {

        if (items != null) {
            holder.binding.setTag(items.get(position));
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if (items != null)
                listener.onTaggedQuestionSelected(items);
        });
        holder.binding.executePendingBindings();
    }

    void addMoreItems(List<TagItems> items, boolean hasMoreItems) {
        final int count = this.items.size();
        this.items.addAll(items);
        onItemsAdded(count, items.size(), hasMoreItems);
    }

    interface TaggedQuestionSelectionListener {
        void onTaggedQuestionSelected(List<TagItems> items);
    }

    static class TaggedQuestionsHolder extends RecyclerView.ViewHolder {
        private final ViewHolderTaggedQuestionItemsBinding binding;

        TaggedQuestionsHolder(ViewHolderTaggedQuestionItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
