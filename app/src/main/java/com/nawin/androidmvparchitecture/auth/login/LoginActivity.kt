package com.nawin.androidmvparchitecture.auth.login

/**
 * Created on 12/13/17.
 */
import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast

import com.nawin.androidmvparchitecture.BaseActivity
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.databinding.ActivityLoginBinding
import com.nawin.androidmvparchitecture.taggedquestion.TaggedQuestionsActivity

import android.text.TextUtils.isEmpty
import com.nawin.androidmvparchitecture.utils.showLoadingDialog

class LoginActivity : BaseActivity(), LoginContract.View {
    private lateinit var presenter: LoginContract.Presenter
    private var progressDialog: ProgressDialog? = null
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        LoginPresenter(component, this)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (isEmpty(username) && isEmpty(password)) {
                binding.etUsername.error = getString(R.string.empty_field_msg)
                binding.etPassword.error = getString(R.string.empty_field_msg)
            } else if (isEmpty(username)) {
                binding.etUsername.error = getString(R.string.empty_field_msg)
            } else if (isEmpty(password)) {
                binding.etPassword.error = getString(R.string.empty_field_msg)
            } else {
                presenter.onLogin(username, password)

            }

        }
    }

    override fun onPause() {
        presenter.stop()
        super.onPause()
    }

    override fun showLoginProgress() {
        progressDialog = showLoadingDialog(this, getString(R.string.verifying_login))
    }

    override fun showLoginSuccess() {
        dismissDialog()
        TaggedQuestionsActivity.start(this)
        finish()
    }

    override fun showLoginError(message: String) {
        dismissDialog()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showNetworkNotAvailableError() {
        dismissDialog()
        Toast.makeText(this, getString(R.string.network_not_available_error), Toast.LENGTH_SHORT).show()
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    private fun dismissDialog() {
            progressDialog!!.dismiss()
    }

}
