package com.nawin.androidmvparchitecture.taggedquestion;


import android.util.Log;

import com.nawin.androidmvparchitecture.MvpComponent;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;
import static com.nawin.androidmvparchitecture.utils.Commons.isEmpty;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
    private final MvpComponent component;
    private TaggedQuestionsContract.View view;
    private Call<BaseResponse<List<Tags>>> call;
    private int offset;

    public TaggedQuestionsPresenter(MvpComponent component, TaggedQuestionsContract.View view) {
        this.component = component;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        this.offset = 1;

        call = component.data().requestTags(new Callback<BaseResponse<List<Tags>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Tags>>> call, Response<BaseResponse<List<Tags>>> response) {
                if (response.isSuccessful()) {
                    BaseResponse<List<Tags>> taggedQuestions = response.body();
                    if (taggedQuestions != null) {
//                        offset += taggedQuestions.getResponse().getTags().size();
//                        List<String> tags = Arrays.asList("a", "b", "c", "d", "e", "f",
//                                "a", "b", "c", "d", "e", "f");
                        view.showTaggedQuestionLoadSuccess(taggedQuestions.getResponse().get(0).getTags(), true); /* false value can be changed to rowTotal > offset */
                    } else {
                        view.showTaggedQuestionLoadError();
                    }
                } else {
                    view.showTaggedQuestionLoadError();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Tags>>> call, Throwable t) {
                view.showTaggedQuestionLoadError();
                Log.d("errors", t.getMessage());
            }
        });

    }

    @Override
    public void stop() {
        cancel(call);
    }

    @Override
    public void onLoadMore() {

        call = component.data().requestTags(new Callback<BaseResponse<List<Tags>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Tags>>> call, Response<BaseResponse<List<Tags>>> response) {
                if (isEmpty(response.body().getResponse())){
                    view.showLoadMoreComplete();
                }else {
                    view.showMoreItems();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Tags>>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onTaggedQuestionSelected(String items) {

    }
}
