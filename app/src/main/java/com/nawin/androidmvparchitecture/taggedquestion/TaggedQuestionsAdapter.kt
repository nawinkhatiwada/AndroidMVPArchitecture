package com.nawin.androidmvparchitecture.taggedquestion

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.data.model.TagItems
import com.nawin.androidmvparchitecture.databinding.ViewHolderTaggedQuestionItemsBinding
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter

/**
 * Created on 12/1/17.
 */
class TaggedQuestionsAdapter internal constructor(recyclerView: RecyclerView,
                                                  private val items: MutableList<TagItems>,
                                                  private val listener: TaggedQuestionSelectionListener) : LoadMoreAdapter<TaggedQuestionsAdapter.TaggedQuestionsHolder>(recyclerView) {


    override fun onCreateViewHolder_(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): TaggedQuestionsHolder =
            TaggedQuestionsHolder(DataBindingUtil.inflate(inflater, R.layout.view_holder_tagged_question_items, parent, false))

    override fun itemCount_() = items.size

    override fun onBindViewHolder_(holder: TaggedQuestionsHolder, position: Int) {

        holder.binding.tag = items[position]

        holder.binding.root.setOnClickListener {
            listener.onTaggedQuestionSelected(items)
        }
        holder.binding.executePendingBindings()
    }

    internal fun addMoreItems(items: List<TagItems>, hasMoreItems: Boolean) {
        val count = this.items.size
        this.items.addAll(items)
        onItemsAdded(count, items.size, hasMoreItems)
    }

    interface TaggedQuestionSelectionListener {
        fun onTaggedQuestionSelected(items: List<TagItems>)
    }

    class TaggedQuestionsHolder(val binding:
                                ViewHolderTaggedQuestionItemsBinding)
        : RecyclerView.ViewHolder(binding.root)
}
