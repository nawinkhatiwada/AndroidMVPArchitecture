package com.nawin.androidmvparchitecture.auth.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.utils.Commons;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this, this);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, getString(R.string.empty_field_msg), Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, getString(R.string.empty_field_msg), Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.onLogin(username, password);
            }
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
    public void showLoginSuccess(String message) {
        dismissDialog();
//       TaggedQuestionsActivity.start(this);
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
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

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void dismissDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
