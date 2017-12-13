package com.nawin.androidmvparchitecture.taggedquestion

import android.content.Context
import com.nawin.androidmvparchitecture.MvpComponent
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.data.Data
import com.nawin.androidmvparchitecture.data.error.FailedResponseException
import com.nawin.androidmvparchitecture.data.error.NetworkNotAvailableException
import com.nawin.androidmvparchitecture.data.model.TagItems
import com.nawin.androidmvparchitecture.data.model.Tags
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created on 9/15/17.
 */

class TaggedQuestionPresenterTest {

    private val context: Context = mock(Context::class.java)
    private val component: MvpComponent = mock(MvpComponent::class.java)
    private val data: Data = mock(Data::class.java)
    private val view: TaggedQuestionsContract.View = mock(TaggedQuestionsContract.View::class.java)
    private lateinit var presenter: TaggedQuestionsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        given(component.context()).willReturn(context)
        given(component.data()).willReturn(data)
        this.presenter = TaggedQuestionsPresenter(component, view)
    }

    @Test
    fun start_successTest() {
        val tagItems = TagItems()
        val tagList = mutableListOf(tagItems, tagItems)
        val itemCount = 3
        val tags = Tags(itemCount, tagList)
        given(data.requestTags(1, 10)).willReturn(Single.just(tags))
        presenter.start()

        verify(view).showProgress()
        argumentCaptor<MutableList<TagItems>>()
                .apply {
                    verify(view).showTagsLoadSuccess(capture(), eq(false))
                    assertEquals(2, allValues[0].size)
                }

    }

    @Test
    fun start_EmptyListTest() {
        whenever(context.getString(R.string.data_not_available)).thenReturn("Empty data")
        val tags = Tags(0, mutableListOf())
        given(data.requestTags(1, 10)).willReturn(Single.just(tags))
        presenter.start()
        verify(view).showProgress()
        verify(view).showEmptyTags(eq("Empty data"))

    }

    @Test
    fun start_failedResponseExceptionTest() {
        whenever(data.requestTags(anyInt(), anyInt())).thenReturn(Single.error(FailedResponseException(-1, "failed")))
        presenter.start()
        verify(view).showProgress()
        verify(view).showTagsLoadError(eq("failed"))
    }

    @Test
    fun start_networkNotAvailableTest() {
        whenever(data.requestTags(anyInt(), anyInt())).thenReturn(Single.error(NetworkNotAvailableException()))
        presenter.start()
        verify(view).showProgress()
        verify(view).showNetworkNotAvailableError()
    }

    @Test
    fun start_responseFailureTest() {
        whenever(context.getString(R.string.server_error)).thenReturn("error")
        val baseResponse = BaseResponse("", 0, null)
        baseResponse.response = null
        `when`(data.requestTags(anyInt(), anyInt())).thenReturn(Single.error(Throwable()))
        presenter.start()
        verify(view).showTagsLoadError(eq("error"))

    }
}
