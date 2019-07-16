package com.e.tremendocSDK.View.UI.Activity;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.e.tremendocSDK.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.e.tremendocSDK.View.UI.UUitil.CallContants.DOCTOR_AVATAR;
import static com.e.tremendocSDK.View.UI.UUitil.CallContants.DOCTOR_NAME;

public class VoiceCall extends AppCompatActivity {

    CircleImageView doctorImage;
    TextView doctorName;
    Bundle bundle;
    String mdoctorName, mdoctorImage, mdoctorToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_call);


        doctorImage=findViewById(R.id.avatar);
        doctorName =findViewById(R.id.doctor_name);
        bundle= getIntent().getExtras();

        mdoctorName=bundle.getString(DOCTOR_NAME);
        mdoctorName=bundle.getString(DOCTOR_AVATAR);

        Picasso.get().load(mdoctorImage).into(doctorImage);
    }



    private void setupVoicecall(){

    }


}
