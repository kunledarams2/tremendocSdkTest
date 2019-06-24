package com.e.tremendocsdk.View.UI.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.e.tremendocsdk.R;
import com.e.tremendocsdk.View.Callback.FragmentChanger;

public class AuthActivity extends BaseActivity implements FragmentChanger {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }


    @Override
    public void changeFragment(Fragment fragment) {

    }
}
