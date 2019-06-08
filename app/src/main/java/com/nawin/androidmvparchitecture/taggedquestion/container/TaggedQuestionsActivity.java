package com.nawin.androidmvparchitecture.taggedquestion.container;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nawin.androidmvparchitecture.BaseActivity;
import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.taggedquestion.TaggedQuestionsFragment;

/**
 * Created by nawin on 6/14/17.
 */

public class TaggedQuestionsActivity extends BaseActivity {

    private static String KEY_USERNAME  = "_username";
    public static void start(Activity activity, String username) {
        Intent intent = new Intent(activity, TaggedQuestionsActivity.class);
        intent.putExtra(KEY_USERNAME,username);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_tagged_questions);
        String username = getIntent().getStringExtra(KEY_USERNAME);

       getSupportFragmentManager().beginTransaction()
               .replace(R.id.container, TaggedQuestionsFragment.getInstance())
               .commit();
    }


}
