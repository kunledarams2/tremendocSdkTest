package com.e.tremendocsdk;

import android.os.Bundle;
import android.view.View;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.arch.R;
//import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.e.tremendocSDK.Audio_sdk;

public class MainActivity extends AppCompatActivity {

    Audio_sdk audio_sdk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        audio_sdk= new Audio_sdk(this);
    }
    public void getView(View view){
//        GetApi(view );
        audio_sdk.setupSDK("userp@gmail.com");
    }
}
