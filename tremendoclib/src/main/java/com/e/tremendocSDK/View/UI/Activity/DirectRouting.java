package com.e.tremendocSDK.View.UI.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.e.tremendocSDK.Api.API;
import com.e.tremendocSDK.Api.StringCall;
import com.e.tremendocSDK.Api.URLS;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.UI.UUitil.IO;
import com.e.tremendocSDK.View.UI.UUitil.ToastUtili;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectRouting extends AppCompatActivity {

    private List<String> getDoctorInfo= new ArrayList<>();
    private StringCall call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_routing);

        getDoctorInfo= getIntent().getStringArrayListExtra("doctorDetails");

        toastMessage(getDoctorInfo.get(0));
        checkDoctor();
    }

    /**
     *  1. check if the doctor is available
     *  2. check if the user/consumer have subscription,
     *  3. check for phone permission for voice call
     *
     *  4. connect to sinch
     *
     * **/
    private void checkDoctor(){

        if(getDoctorInfo.get(0)!=null){
            Intent intent =new Intent(this, ContactActivity.class);
            startActivity(intent);

        }

    }

    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


    private void initialConsultation(){
        call=new StringCall(this);
        Map<String, String> params=new HashMap<>();

        params.put("consultationType","AUDIO");
        params.put("patientId", API.getCustomerId(this));
        params.put("doctorId",getDoctorInfo.get(0));
        params.put("specialtyId",getDoctorInfo.get(1));

        call.post(URLS.INITIATE_CONSULTATION,params, response -> {

            try{
                JSONObject obj = new JSONObject(response);
                if(obj.has("code")&& obj.getInt("code")==0 && !obj.isNull("code")){

                }
            }catch (JSONException e){

            }

        },error -> {});






    }

}
