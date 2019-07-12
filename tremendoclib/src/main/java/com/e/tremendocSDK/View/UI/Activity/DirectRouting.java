package com.e.tremendocSDK.View.UI.Activity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.e.tremendocSDK.Api.API;
import com.e.tremendocSDK.Api.StringCall;
import com.e.tremendocSDK.Api.URLS;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.UI.UUitil.IO;
import com.e.tremendocSDK.View.UI.UUitil.ToastUtili;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.logging.Formatter;

import static com.e.tremendocSDK.View.UI.UUitil.CallContants.CONSULTATION_ID;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.DOCTOR_AVATAR;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.DOCTOR_ID;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.DOCTOR_TOKEN;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.DOCTOR_UUID;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.PATIENT_TOKEN;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.SERVER_KEY;

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
        toastMessage(getDoctorInfo.get(1));
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
//            Intent intent =new Intent(this, ContactActivity.class);
//            startActivity(intent);
            initialConsultation();
        }


    }

    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


    private void initialConsultation(){
        call=new StringCall(this);
        int specialtyId= 1;
        Map<String, String> params=new HashMap<>();

        params.put("consultationType","AUDIO");
        params.put("patientId", API.getCustomerId(this));
        params.put("doctorId",getDoctorInfo.get(0));
        params.put("specialtyId", String.valueOf(specialtyId));

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

                    Bundle bundle= new Bundle();
                    bundle.putString(DOCTOR_ID, doctorId);
                    bundle.putString(SERVER_KEY,firebaseserverKey);
                    bundle.putString(DOCTOR_UUID,doctcontId);
                    bundle.putString(DOCTOR_TOKEN, doctorPushToken);
                    bundle.putString(PATIENT_TOKEN, patientPushToken);
                    bundle.putString(CONSULTATION_ID, consultationId);
                    bundle.putString(DOCTOR_ID, String.valueOf(doctorId));
                    bundle.putString(DOCTOR_AVATAR, doctorImage);


                    Intent intent = new Intent(this,ContactActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
                else {
                    log(obj.getString("description"));
                    errorShowlog(obj.getString("description"), errorMessage.unknown);
                }
            }catch (JSONException e){
                log(e.getMessage());
            }

        },error -> {
            log("VOLLEY ERROR");
            log(error.getMessage());
            if(error.networkResponse==null){
                log("Network Error");
                errorShowlog("Please Check Your Network Service",errorMessage.NetworkError);
            }
            else {
                String errMsg = String.valueOf(error.networkResponse.data);
                errorShowlog(errMsg, errorMessage.unknown);
                log("DATA: " + errMsg);
            }
        });

    }

    private void errorShowlog(String msg, errorMessage error){

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
            isPosBtnDialog=true;
            DialogInterface.dismiss();
        });

        if(error.equals(errorMessage.NetworkError) || error.equals(errorMessage.unknown)) {
            builder.setOnDismissListener(dialogInterface -> {

                if(isPosBtnDialog){
                    initialConsultation();
                    isPosBtnDialog=false;
                }
                else {
                    goBack();
                }
            });

            builder.setOnCancelListener( dialogInterface -> {

                if(isPosBtnDialog){
                    initialConsultation();
                    isPosBtnDialog=false;
                }
                else {
                    goBack();
                }
            });

        }
        else  if(error.equals(errorMessage.noSubcription)){
            builder.setOnCancelListener(dialogInterface -> {
                if(isPosBtnDialog){
                    isPosBtnDialog=false;
                }else {
                    goBack();
                }
            });
            builder.setOnDismissListener(dialogInterface -> {
                if(isPosBtnDialog){

                    isPosBtnDialog=false;
                }else {
                    goBack();
                }
            });
        }
        alertDialog= builder.create();
        alertDialog.show();
    }

    private void goBack(){
        Intent intent = new Intent(this, Finddoctor.class);
        startActivity(intent);
        finish();
    }

    private void log(String e){
        Log.e("Routing", "_-_________-------------________" +e);
    }
}
