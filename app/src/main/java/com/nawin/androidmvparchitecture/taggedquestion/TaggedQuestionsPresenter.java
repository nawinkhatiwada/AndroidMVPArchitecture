package com.nawin.androidmvparchitecture.taggedquestion;


import android.content.Context;
import android.util.Log;

import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;

/**
 * Created by nawin on 6/14/17.
 */

public class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
    private Context context;
    private TaggedQuestionsContract.View view;
    private Call<BaseResponse<List<Tags>>> call;
    private int offset;

    public TaggedQuestionsPresenter(Context context, TaggedQuestionsContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        this.offset = 1;
        call = Data.getInstance(context).requestTags(new Callback<BaseResponse<List<Tags>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Tags>>> call, Response<BaseResponse<List<Tags>>> response) {
                if (response.isSuccessful()) {
                    BaseResponse<List<Tags>> taggedQuestions = response.body();
                    if (taggedQuestions != null) {
//                        offset += taggedQuestions.getItems().getTags().size();
                        view.showTaggedQuestionLoadSuccess(taggedQuestions.getItems().get(0).getTags(), true); /* false value can be changed to rowTotal > offset */
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

    }

    @Override
    public void onTaggedQuestionSelected(String items) {

    }
}
