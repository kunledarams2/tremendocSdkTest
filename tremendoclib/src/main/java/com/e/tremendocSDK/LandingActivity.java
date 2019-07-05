package com.e.tremendocSDK;

//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.tremendocSDK.View.UI.Activity.Authm;
import com.e.tremendocSDK.View.UI.Activity.Sign_up;
import com.e.tremendocSDK.View.UI.Activity.Signup;

public class LandingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

//
//    public void GetApi(View view) {
//
//        try {
//            Intent intent= new Intent(this, Authm.class);
////            intent.putExtra("userEmail",userEmail);
//            startActivity(intent);
//        }catch (Exception e){
//            Log.e("tremendocSDK",e.getMessage());
//        }
//
//    }

    public  void Authn(String userEmail ){

        if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            Intent intent= new Intent(this,Authm.class);
            intent.putExtra("userEmail",userEmail);
            startActivity(intent);
        }
        else Toast.makeText(this,"Wrong Email",Toast.LENGTH_LONG).show();


    }
}
