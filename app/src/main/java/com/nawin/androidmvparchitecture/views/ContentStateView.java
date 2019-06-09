package com.nawin.androidmvparchitecture.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.databinding.ContentStateViewBinding;

import static com.nawin.androidmvparchitecture.utils.Commons.checkNotNull;


public class ContentStateView extends FrameLayout {
    private ContentStateViewBinding binding;
    @NonNull
    private View contentView;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public ContentStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.content_state_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setContent(View contentView) {
        this.contentView = contentView;
    }

    public void showProgress() {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(VISIBLE);
        this.contentView.setVisibility(GONE);
        this.binding.pbConnectionState.setVisibility(VISIBLE);
        this.binding.ivContentState.setVisibility(GONE);
        this.binding.tvConnectionState.setVisibility(GONE);
    }

    public void showProgress(String message) {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(VISIBLE);
        this.binding.tvConnectionState.setText(message);
        this.binding.tvConnectionState.setVisibility(VISIBLE);
        this.binding.pbConnectionState.setVisibility(VISIBLE);
        this.binding.ivContentState.setVisibility(GONE);
    }

    public void showError(@StringRes int message) {
        this.binding.tvConnectionState.setText(message);
        errorVisible();
    }

    public void showError(@DrawableRes int image, @StringRes int message) {
        this.binding.tvConnectionState.setText(message);
        binding.ivContentState.setImageDrawable(ContextCompat.getDrawable(binding.ivContentState.getContext(),image));
        errorVisible();
    }

    public void showError(@DrawableRes int image, String message) {
        this.binding.tvConnectionState.setText(message);
        binding.ivContentState.setImageDrawable(ContextCompat.getDrawable(binding.ivContentState.getContext(),image));
        errorVisible();
    }

    public void showError(String message) {
        this.binding.tvConnectionState.setText(message);
        errorVisible();
    }

    public void showError() {
        this.binding.tvConnectionState.setText("");
        errorVisible();
    }

    private void errorVisible() {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(VISIBLE);
        this.binding.pbConnectionState.setVisibility(GONE);
        this.binding.ivContentState.setVisibility(VISIBLE);
        this.binding.tvConnectionState.setVisibility(VISIBLE);
        this.contentView.setVisibility(GONE);
    }

    public void showContent() {
        checkNotNull(contentView, "content view == null, must call setContent(View)");
        setVisibility(GONE);
        contentView.setVisibility(VISIBLE);
    }
}