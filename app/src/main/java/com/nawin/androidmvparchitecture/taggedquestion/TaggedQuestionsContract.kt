package com.nawin.androidmvparchitecture.taggedquestion

import com.nawin.androidmvparchitecture.BasePresenter
import com.nawin.androidmvparchitecture.BaseView
import com.nawin.androidmvparchitecture.data.model.TagItems
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter

/**
 * Created on 11/30/17.
 */
interface TaggedQuestionsContract {

    interface View : BaseView<Presenter> {
        fun showProgress()

        fun showTagsLoadSuccess(items: MutableList<TagItems>, hasMoreItems: Boolean)

        fun showTagsLoadError(message: String)

        fun showEmptyTags(message: String)

        fun showLoadMoreProgress()

        fun showMoreTags(items: MutableList<TagItems>, hasMoreItems: Boolean)

        fun showLoadMoreError()

        fun onLoadComplete()

        fun onLogoutSelection()

        fun showNetworkNotAvailableError()
    }

    interface Presenter : BasePresenter, LoadMoreAdapter.LoadMoreListener,
            TaggedQuestionsAdapter.TaggedQuestionSelectionListener {

        fun onLogout()
    }
}