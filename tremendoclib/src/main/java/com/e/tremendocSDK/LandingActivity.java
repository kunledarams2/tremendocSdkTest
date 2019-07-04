package com.e.tremendocSDK;

//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.e.tremendocSDK.View.UI.Activity.Sign_up;
import com.e.tremendocSDK.View.UI.Activity.Signup;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void GetApi(View view) {

//        Toast.makeText(this,"Get Tremendoc Api",Toast.LENGTH_LONG).show();
        try {
            Intent intent= new Intent(this, Signup.class);
            startActivity(intent);
        }catch (Exception e){
            Log.e("tremendocSDK",e.getMessage());
        }

    }
}
