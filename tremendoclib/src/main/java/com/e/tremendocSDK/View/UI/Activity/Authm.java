package com.e.tremendocSDK.View.UI.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.e.tremendocSDK.Api.API;
import com.e.tremendocSDK.Api.StringCall;
import com.e.tremendocSDK.Api.URLS;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.UI.UUitil.IO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Authm extends AppCompatActivity {

    private EditText email;
    private StringCall call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authm);

        email= findViewById(R.id.email);
        call= new StringCall(this);
    }

    /**
    *  Host Apps to provide their user email for query
     *  to check the existence of the user on the SDK
    * */
    private void getEmail(String userEmail){
        email.setText(userEmail);

        if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            Map<String, String> logParams= new HashMap<>();

            logParams.put("email", userEmail);
            logParams.put("brand", Build.BRAND);
            logParams.put("operatingSystem", "ANDROID");
            logParams.put("uuid", IO.getData(this, API.MY_UUID));

            call.post(URLS.SDK_AUTHENICATION, logParams,response -> {

                try{
                    JSONObject obj =new JSONObject(response);

                    if(obj.has("code") && obj.getInt("code")==0){

                        Intent intent= new Intent(this,Finddoctor.class);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        Intent intent= new Intent(this,Signup.class);
                        startActivity(intent);
                        finish();
                    }

                }catch (JSONException e){
                    Log.e("Authentication Error", e.getMessage());
                }

            },error -> {
                if(error.networkResponse==null){
                    Toast.makeText(this,"Please check your internet ",Toast.LENGTH_LONG).show();
                }
            });


        }


    }
}
