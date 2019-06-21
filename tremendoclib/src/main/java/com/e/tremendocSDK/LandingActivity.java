package com.e.tremendocSDK;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void GetApi(View view) {

        Toast.makeText(this,"Get Tremendoc Api",Toast.LENGTH_LONG).show();
    }
}
