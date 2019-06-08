package com.nawin.androidmvparchitecture.auth.login;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.nawin.androidmvparchitecture.BaseActivity;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.databinding.ActivityLoginBinding;
import com.nawin.androidmvparchitecture.taggedquestion.TaggedQuestionsActivity;
import com.nawin.androidmvparchitecture.utils.Commons;

import javax.inject.Inject;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
//        new LoginPresenter(component, this);

        binding.btnLogin.setOnClickListener(v -> {
            String username = binding.etUsername.getText().toString();
            if (isEmpty(username)) {
                binding.etUsername.setError(getString(R.string.empty_field_msg));
                return;
            }
            String password = binding.etPassword.getText().toString();
            if (isEmpty(password)) {
                binding.etPassword.setError(getString(R.string.empty_field_msg));
                return;
            }
            presenter.onLogin(username, password);
        });
    }

    @Override
    protected void onPause() {
        presenter.stop();
        super.onPause();
    }

    @Override
    public void showLoginProgress() {
        progressDialog = Commons.showLoadingDialog(this, getString(R.string.verifying_login));
    }

    @Override
    public void showLoginSuccess() {
        dismissDialog();
        TaggedQuestionsActivity.start(this);
        finish();
    }

    @Override
    public void showLoginError(String message) {
        dismissDialog();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showNetworkNotAvailableError() {
        dismissDialog();
        Toast.makeText(this, getString(R.string.network_not_available_error), Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void setPresenter(LoginContract.Presenter presenter) {
//        this.presenter = presenter;
//    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
