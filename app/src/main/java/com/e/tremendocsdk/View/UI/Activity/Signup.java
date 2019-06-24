package com.e.tremendocsdk.View.UI.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.e.tremendocsdk.R;

public class Signup extends BaseActivity{

    public  static final  String STEP_ONE="step_one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
}
