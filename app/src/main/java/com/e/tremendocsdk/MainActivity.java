package com.e.tremendocsdk;

import android.os.Bundle;
import android.view.View;
//import com.e.tremendocSDK.R;
//import android.R;
import androidx.appcompat.app.AppCompatActivity;

import com.e.tremendocSDK.LandingActivity;

public class MainActivity extends AppCompatActivity {

    private  LandingActivity landingActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//

        setContentView(R.layout.activity_main);
    }
    public void getView(View view){
//        GetApi(view );
        landingActivity.Authn("user@gmail.com ");
    }
}
