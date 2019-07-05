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
import com.e.tremendocSDK.View.UI.UUitil.DeviceName;
import com.e.tremendocSDK.View.UI.UUitil.IO;
import com.e.tremendocSDK.View.UI.UUitil.ToastUtili;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Authm extends AppCompatActivity {

    private EditText email;
    private StringCall call;
    private String getHostuseremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authm);

        email= findViewById(R.id.email);
//        call= new StringCall(this);
        getHostuseremail=getIntent().getStringExtra("userEmail");
        getEmail();
    }

    /**
    *  Host Apps to provide their user email for query
     *  to check the existence of the user on the SDK
    * */
   public void getEmail(){
        email.setText(getHostuseremail);
        call=new StringCall(this);
        Toast.makeText(this, getHostuseremail,Toast.LENGTH_LONG).show();

       Map<String, String> logParams= new HashMap<>();

       logParams.put("email", getHostuseremail);
       logParams.put("brand", DeviceName.getDeviceName());
       logParams.put("operatingSystem", "ANDROID");
       logParams.put("uuid", IO.getData(this, API.MY_UUID));
       logParams.put("sdkType","chat");
       logParams.put("provider","1");


       call.post(URLS.SDK_AUTHENICATION, logParams,response -> {

           ToastUtili.showModal(this,response);
           try{
               JSONObject obj =new JSONObject(response);

               if(obj.has("code") && obj.getInt("code")==0){

                   Toast.makeText(this, "Welcome",Toast.LENGTH_LONG).show();
                   Intent intent= new Intent(this,Finddoctor.class);
                   startActivity(intent);
                   finish();

               }
               else if(obj.has("description")){

                   Intent intent= new Intent(this,Signup.class);
                   startActivity(intent);
                   finish();

               }

           }catch (JSONException e){
               ToastUtili.showModal(this,e.getMessage());
               Log.e("Authentication Error", e.getMessage());
           }

       },error -> {
           if(error.networkResponse==null){
               Toast.makeText(this,"Please check your internet ",Toast.LENGTH_LONG).show();
           }
       });


       if(Patterns.EMAIL_ADDRESS.matcher(getHostuseremail).matches()){

        }


    }
}
