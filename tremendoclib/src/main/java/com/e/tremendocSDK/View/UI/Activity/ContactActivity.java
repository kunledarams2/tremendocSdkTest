package com.e.tremendocSDK.View.UI.Activity;

//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Fragment.Contact.AudioCall;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;

public class ContactActivity extends AppCompatActivity implements FragmentChanger {

    public static final String VOICE_CALL= "voice_call";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        changeFragment(VOICE_CALL);
    }

    @Override
    public void ChangeFragment(FragmentTitled fragment) {
        changeFragment(fragment);
    }

    private  void changeFragment(Fragment fragment){

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();

    }
    private void changeFragment(String fragmntTitle){

        Fragment fragment;
        setTitle(fragmntTitle);
        switch (fragmntTitle){
            case VOICE_CALL:
                fragment= AudioCall.newInstance();
                break;
                default:
                    fragment= AudioCall.newInstance();

        }


    }
}
