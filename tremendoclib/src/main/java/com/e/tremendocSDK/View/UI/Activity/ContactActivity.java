package com.e.tremendocSDK.View.UI.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Fragment.Contact.AudioCall;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;

public class ContactActivity extends AppCompatActivity implements FragmentChanger {

    public static final String VOICE_CALL= "voice_call";
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ChangeFragment(AudioCall.newInstance());

    }

    @Override
    public void ChangeFragment(FragmentTitled fragment) {
        currentFragment=fragment;
        changeView(fragment);
    }

    private  void changeView(Fragment fragment){

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();

    }

}
