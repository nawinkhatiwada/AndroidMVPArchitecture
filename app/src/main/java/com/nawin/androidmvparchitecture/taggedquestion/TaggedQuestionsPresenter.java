package com.nawin.androidmvparchitecture.taggedquestion;


import android.content.Context;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.Data;
import com.nawin.androidmvparchitecture.data.model.TagItems;
import com.nawin.androidmvparchitecture.data.model.Tags;
import com.nawin.androidmvparchitecture.data.model.api.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nawin.androidmvparchitecture.utils.Commons.cancel;
import static com.nawin.androidmvparchitecture.utils.Commons.isEmpty;

/**
 * Created by nawin on 6/14/17.
 */

class TaggedQuestionsPresenter implements TaggedQuestionsContract.Presenter {
    private final int LIMIT = 10;
    private Context context;
    private TaggedQuestionsContract.View view;
    private Call<BaseResponse<Tags>> call;
    private int offset;

    TaggedQuestionsPresenter(Context context, TaggedQuestionsContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.showProgress();
        this.offset = 0;
        call = Data.getInstance(context).requestTags(offset, LIMIT, new Callback<BaseResponse<Tags>>() {
            @Override
            public void onResponse(Call<BaseResponse<Tags>> call, Response<BaseResponse<Tags>> response) {
                if (response != null && response.isSuccessful()) {
                    int itemCount = response.body().getResponse().getItemCount();
                    List<TagItems> items = response.body().getResponse().getItems();
                    if (itemCount > 0 && !isEmpty(items)) {
                        final int count = items.size();
                        offset += count;
                        view.showTagsLoadSuccess(items, itemCount > offset);
                    } else {
                        view.showEmptyTags(context.getString(R.string.data_not_available));
                    }
                } else {
                    view.showTagsLoadError(context.getString(R.string.server_error));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Tags>> call, Throwable t) {
                view.showTagsLoadError(context.getString(R.string.server_error));
            }
        });
    }

    @Override
    public void stop() {
        cancel(call);
    }

    @Override
    public void onLoadMore() {
        view.showLoadMoreProgress();
        call = Data.getInstance(context).requestTags(offset, LIMIT, new Callback<BaseResponse<Tags>>() {

            @Override
            public void onResponse(Call<BaseResponse<Tags>> call, Response<BaseResponse<Tags>> response) {
                if (response != null && response.isSuccessful()) {
                    int itemCount = response.body().getResponse().getItemCount();
                    List<TagItems> items = response.body().getResponse().getItems();
                    if (itemCount > 0 && !isEmpty(items)) {
                        final int count = items.size();
                        offset += count;
                        view.showMoreTags(items, itemCount > offset);
                    } else {
                        view.onLoadComplete();
                    }
                } else {
                    view.showLoadMoreError();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Tags>> call, Throwable t) {
                view.showLoadMoreError();
            }
        });
    }

    @Override
    public void onTaggedQuestionSelected(List<TagItems> items) {

    }
}
