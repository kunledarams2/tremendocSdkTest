package com.e.tremendocSDK.View.UI.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.UI.UUitil.ToastUtili;

public class DirectRouting extends AppCompatActivity {

    private  String getDoctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_routing);

        getDoctorId= getIntent().getStringExtra("doctorId");

        toastMessage(getDoctorId);

        checkDoctor();
    }

    // checking if the doctor is available
    private void checkDoctor(){

        if(getDoctorId!=null){
            Intent intent =new Intent(this, ContactActivity.class);
            startActivity(intent);

        }

    }

    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }




}
