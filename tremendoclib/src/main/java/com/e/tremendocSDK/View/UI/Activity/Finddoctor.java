package com.e.tremendocSDK.View.UI.Activity;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.e.tremendocSDK.Audio_sdk;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Fragment.Finddoctor.FindADoctor;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;

public class Finddoctor extends AppCompatActivity implements FragmentChanger {

    public static  final String CHAT_WITH_DOCTOR="chat_with_doctor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findadoctor);
        changeView(CHAT_WITH_DOCTOR);
    }


    @Override
    public void ChangeFragment(FragmentTitled fragment) {
        changeView(fragment);
    }

    private void changeView(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();

    }
    private void changeView(String fragmentTitle){
        Fragment fragment;
        setTitle(fragmentTitle);
        switch (fragmentTitle){
            case CHAT_WITH_DOCTOR: fragment= FindADoctor.newInstance(1);
            break;
            default:fragment= FindADoctor.newInstance(1);

        }
        changeView(fragment);

    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(this, Audio_sdk.class);
//        startActivity(intent);
//        finish();

        onBackPressed();
        finish();
    }
}
