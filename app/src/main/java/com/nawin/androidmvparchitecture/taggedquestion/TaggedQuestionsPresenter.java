package com.nawin.androidmvparchitecture.taggedquestion;


import android.content.Context;

import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.local.LocalRepo;
import com.nawin.androidmvparchitecture.data.model.News;
import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;
import com.nawin.androidmvparchitecture.data.model.UserInfo;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
    private Context context;
    private TaggedQuestionsContract.View view;
    private Call<BaseResponse<List<TaggedQuestions>>> calls;
    private int offset;

    public TaggedQuestionsPresenter(Context context, TaggedQuestionsContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        this.offset = 1;
        calls = Data.getInstance(context).requestTaggedQuestion(new Callback<BaseResponse<List<TaggedQuestions>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<TaggedQuestions>>> call, Response<BaseResponse<List<TaggedQuestions>>> response) {
                if (response.isSuccessful()) {
                    List<TaggedQuestions> taggedQuestions = response.body().getResponse();
                    if (taggedQuestions != null) {
                        offset += taggedQuestions.size();
                        view.showTaggedQuestionLoadSuccess(taggedQuestions, false); /* false value can be changed to rowTotal > offset */
                    } else {
                        view.showTaggedQuestionLoadError();
                    }
                } else {
                    view.showTaggedQuestionLoadError();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<TaggedQuestions>>> call, Throwable t) {
                view.showTaggedQuestionLoadError();
            }
        });
    }

    @Override
    public void stop() {
        cancel(calls);
    }
}
