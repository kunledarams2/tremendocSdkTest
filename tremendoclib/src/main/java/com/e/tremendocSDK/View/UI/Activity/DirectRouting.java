package com.e.tremendocSDK.View.UI.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    private enum errorMessage { noSubcription, unknown , NetworkError}
    private AlertDialog alertDialog;
    private boolean isPosBtnDialog= false;

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

                    String doctcontId=obj.getString("doctorConnectionId");
                    String consumercontId= obj.getString("consumerConnectionId");
                    String consumerId=obj.getString("consumerId");
                    String doctorId= obj.getString("doctorId");
                    String doctorImage= obj.getString("doctorImage");
                    String doctorPushToken= obj.getString("doctorPushToken");
                    String patientPushToken= obj.getString("patientPushToken");
                    String firebaseserverKey =obj.getString("firebaseServerKey");
                    String consultationId= obj.getString("consultationId");

                }
            }catch (JSONException e){

            }

        },error -> {
            if(error.networkResponse==null){

            }
        });

    }

    private void errorShowlog(String msg, errorMessage error){

        if(DirectRouting.this==null) return;
        if(alertDialog!=null&& alertDialog.isShowing()) return;

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setNegativeButton("Cancel", (DInterface, i)->{
            DInterface.dismiss();
        });

        String stBtn="";

        if(error.equals(errorMessage.NetworkError)  || error.equals(errorMessage.unknown)){
            stBtn="Retry";
        } else if(error.equals(errorMessage.noSubcription)){
            stBtn="Subscribe";
        }

        builder.setPositiveButton(stBtn, (DialogInterface,i)->{
            DialogInterface.dismiss();
        });

        if(error.equals(errorMessage.NetworkError) || error.equals(errorMessage.unknown)){

        }

    }

    private void goBack(){
        Intent intent = new Intent(this, Finddoctor.class);
        startActivity(intent);
        finish();
    }
}
