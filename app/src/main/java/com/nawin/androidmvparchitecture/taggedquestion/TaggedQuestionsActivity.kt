package com.nawin.androidmvparchitecture.taggedquestion

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.nawin.androidmvparchitecture.BaseActivity
import com.nawin.androidmvparchitecture.R
import com.nawin.androidmvparchitecture.auth.login.LoginActivity
import com.nawin.androidmvparchitecture.data.model.TagItems
import com.nawin.androidmvparchitecture.databinding.ActivityTaggedQuestionsBinding
import com.nawin.androidmvparchitecture.utils.showLoadingDialog

/**
 * Created on 11/30/17.
 */
class TaggedQuestionsActivity : BaseActivity(), TaggedQuestionsContract.View {
    private lateinit var presenter: TaggedQuestionsContract.Presenter
    private lateinit var binding: ActivityTaggedQuestionsBinding
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tagged_questions)
        val isLoggedIn = data.isLoggedIn
        if (!isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        presenter = TaggedQuestionsPresenter(component, this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onPause() {
        presenter.stop()
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                onLogoutSelection()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setPresenter(presenter: TaggedQuestionsContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProgress() {
        progressDialog = showLoadingDialog(this)
    }

    override fun showTagsLoadSuccess(items: MutableList<TagItems>, hasMoreItems: Boolean) {
        dismissDialog()
        var adapter: TaggedQuestionsAdapter?
        if (binding.rvTaggedQuestion.adapter != null) {
            adapter = binding.rvTaggedQuestion.adapter as TaggedQuestionsAdapter?
            adapter?.detachLoadMore()
        }
        adapter = TaggedQuestionsAdapter(binding.rvTaggedQuestion, items, presenter)

        if (hasMoreItems)
            adapter.attachLoadMore(presenter)
        binding.rvTaggedQuestion.adapter = adapter
    }

    override fun showTagsLoadError(message: String) {
        dismissDialog()
        Toast.makeText(this@TaggedQuestionsActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyTags(message: String) {
        dismissDialog()
        Toast.makeText(this@TaggedQuestionsActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoadMoreProgress() {

    }

    override fun showMoreTags(items: MutableList<TagItems>, hasMoreItems: Boolean) {
        (binding.rvTaggedQuestion.adapter as TaggedQuestionsAdapter).addMoreItems(items, hasMoreItems)
    }

    override fun showLoadMoreError() {
        (binding.rvTaggedQuestion.adapter as TaggedQuestionsAdapter).onLoadError()
    }

    override fun onLoadComplete() {
        (binding.rvTaggedQuestion.adapter as TaggedQuestionsAdapter).onLoadComplete()

    }

    override fun onLogoutSelection() {
        presenter.onLogout()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showNetworkNotAvailableError() {
        dismissDialog()
        Toast.makeText(this@TaggedQuestionsActivity, getString(R.string.network_not_available_error), Toast.LENGTH_SHORT).show()

    }

    private fun dismissDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, TaggedQuestionsActivity::class.java)
            activity.startActivity(intent)
        }
    }
}