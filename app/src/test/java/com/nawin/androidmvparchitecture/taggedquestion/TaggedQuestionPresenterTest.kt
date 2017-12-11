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

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import io.reactivex.Single

import org.junit.Assert.assertEquals
import org.mockito.ArgumentCaptor.forClass
import org.mockito.Matchers.anyInt
import org.mockito.Matchers.eq
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.*


/**
 * Created on 9/15/17.
 */

class TaggedQuestionPresenterTest {

    @Mock
     var context: Context? = null

    @Mock
     var component: MvpComponent? = null

    @Mock
     var data: Data? = null

    @Mock
     var view: TaggedQuestionsContract.View? = null

    private lateinit var presenter: TaggedQuestionsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(component!!.context()).thenReturn(context)
        `when`(component!!.data()).thenReturn(data)
        this.presenter = TaggedQuestionsPresenter(component!!, view!!)
    }

    @Test
    fun start_successTest() {
        val tagItems = TagItems()
        val tags = Tags()
        tags.itemCount = 3
        val tagList : MutableList<TagItems> = mutableListOf(tagItems,tagItems)
        val argumentCaptor = forClass<List<TagItems>, MutableList<TagItems>>(List::class.java)
        tags.items = tagList
        `when`(data!!.requestTags(anyInt(), anyInt())).thenReturn(Single.just(tags))
        presenter.start()
        verify(view)?.showTagsLoadSuccess(argumentCaptor.capture() as MutableList<TagItems>, eq(false))
        assertEquals(2, argumentCaptor.value.size.toLong())
    }

    @Test
    fun start_EmptyListTest() {
        `when`(context!!.getString(R.string.data_not_available)).thenReturn("Empty data")
        val tags = Tags()
        tags.itemCount = 0
        val items = ArrayList<TagItems>()
        tags.items = items
        `when`(data!!.requestTags(anyInt(), anyInt())).thenReturn(Single.just(tags))
        presenter.start()
        verify<TaggedQuestionsContract.View>(view).showEmptyTags(eq("Empty data"))
    }

    @Test
    fun start_failedResponseExceptionTest() {
        `when`(data!!.requestTags(anyInt(), anyInt())).thenReturn(Single.error(FailedResponseException(-1, "failed")))
        presenter.start()
        verify<TaggedQuestionsContract.View>(view).showTagsLoadError(eq("failed"))
    }

    @Test
    fun start_networkNotAvailableTest() {
        `when`(data!!.requestTags(anyInt(), anyInt())).thenReturn(Single.error(NetworkNotAvailableException()))
        presenter.start()
        verify<TaggedQuestionsContract.View>(view).showNetworkNotAvailableError()
    }

    @Test
    fun start_responseFailureTest() {
        `when`(context!!.getString(R.string.server_error)).thenReturn("error")
        val baseResponse = BaseResponse("",0, null)
        baseResponse.response = null
        `when`(data!!.requestTags(anyInt(), anyInt())).thenReturn(Single.error(Throwable()))
        presenter!!.start()
        verify<TaggedQuestionsContract.View>(view).showTagsLoadError(eq("error"))

    }

}
