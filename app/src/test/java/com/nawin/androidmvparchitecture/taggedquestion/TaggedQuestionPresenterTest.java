package com.nawin.androidmvparchitecture.taggedquestion;

import android.content.Context;

import com.nawin.androidmvparchitecture.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created on 9/15/17.
 */

public class TaggedQuestionPresenterTest {

    @Mock
    Context context;

    @Mock
    MvpComponent component;

    @Mock
    Data data;

    @Mock
    TaggedQuestionsContract.View view;

    private TaggedQuestionsPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(component.context()).thenReturn(context);
        when(component.data()).thenReturn(data);
        this.presenter = new TaggedQuestionsPresenter(component, view);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void start_successTest() {
        TagItems tagItems = new TagItems();
        Tags tags = new Tags();
        tags.setItemCount(3);
        List<TagItems> tagList = Arrays.asList(tagItems, tagItems);
        ArgumentCaptor<List<TagItems>> argumentCaptor = ArgumentCaptor.forClass((Class) List.class);
        tags.setItems(tagList);
        when(data.requestTags(anyInt(), anyInt())).thenReturn(Single.just(tags));
        presenter.start();
        verify(view).showTagsLoadSuccess(argumentCaptor.capture(), eq(false));
        assertEquals(2, argumentCaptor.getValue().size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void start_EmptyListTest() {
        when(context.getString(R.string.data_not_available)).thenReturn("Empty data");
        Tags tags = new Tags();
        tags.setItemCount(0);
        List<TagItems> items = new ArrayList<>();
        tags.setItems(items);
        when(data.requestTags(anyInt(), anyInt())).thenReturn(Single.just(tags));
        presenter.start();
        verify(view).showEmptyTags(eq("Empty data"));
    }

    @Test
    public void start_failedResponseExceptionTest() {
        when(data.requestTags(anyInt(), anyInt())).thenReturn(Single.error(new FailedResponseException(-1, "failed")));
        presenter.start();
        verify(view).showTagsLoadError(eq("failed"));
    }

    @Test
    public void start_networkNotAvailableTest() {
        when(data.requestTags(anyInt(), anyInt())).thenReturn(Single.error(new NetworkNotAvailableException()));
        presenter.start();
        verify(view).showNetworkNotAvailableError();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void start_responseFailureTest() {
        when(context.getString(R.string.server_error)).thenReturn("error");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponse(null);
        when(data.requestTags(anyInt(), anyInt())).thenReturn(Single.error(new Throwable()));
        presenter.start();
        verify(view).showTagsLoadError(eq("error"));

    }

}
