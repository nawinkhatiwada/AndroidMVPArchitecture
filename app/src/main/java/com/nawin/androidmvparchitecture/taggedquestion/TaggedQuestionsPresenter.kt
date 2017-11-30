package com.nawin.androidmvparchitecture.taggedquestion

import com.nawin.androidmvparchitecture.MvpComponent
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.data.error.FailedResponseException
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException
import com.nawin.androidmvparchitecture.data.model.TagItems
import io.reactivex.disposables.Disposable

/**
 * Created on 11/30/17.
 */
internal class TaggedQuestionsPresenter(private val component: MvpComponent,
                                        private val view: TaggedQuestionsContract.View)
    : TaggedQuestionsContract.Presenter {

    private var disposable: Disposable? = null
    private var offset: Int = 0

    init {
        view.setPresenter(this)
    }

    override fun start() {
        view.showProgress()
        this.offset = 1
        disposable = component.data().requestTags(offset, LIMIT)
                .subscribe({ response ->
                    val itemCount = response.itemCount
                    val items = response.items
                    if (items != null) {
                        if (itemCount > 0 && !items.isEmpty()) {
                            val count = items.size
                            offset += count
                            view.showTagsLoadSuccess(items, itemCount > offset)
                        } else {
                            view.showEmptyTags(component.context().getString(R.string.data_not_available))
                        }
                    } else {
                        view.showTagsLoadError(component.context().getString(R.string.server_error))
                    }

                }) { throwable ->
                    when (throwable) {
                        is FailedResponseException -> view.showTagsLoadError(throwable.message)
                        is NetworkNotAvailableException -> view.showNetworkNotAvailableError()
                        else -> view.showTagsLoadError(component.context().getString(R.string.server_error))
                    }
                }
    }

    override fun stop() {
        disposable?.dispose()
    }

    override fun onLoadMore() {
        view.showLoadMoreProgress()
        disposable = component.data().requestTags(offset, LIMIT)
                .subscribe({ response ->
                    val itemCount = response.itemCount
                    val items = response.items
                    if (items != null) {
                        if (itemCount > 0 && !items.isEmpty()) {
                            val count = items.size
                            offset += count
                            view.showMoreTags(items, itemCount > offset)
                        } else {
                            view.onLoadComplete()
                        }
                    } else {
                        view.showLoadMoreError()
                    }
                }) { /*Error*/ view.showLoadMoreError() }

    }

    override fun onLogout() {
        component.data().logout()
    }

    override fun onTaggedQuestionSelected(items: List<TagItems>) {}

    companion object {
        private val LIMIT = 10
    }
}
