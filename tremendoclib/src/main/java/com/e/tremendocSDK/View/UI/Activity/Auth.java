package com.e.tremendocSDK.View.UI.Activity;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;
import com.e.tremendocSDK.View.UI.Fragment.Signup.stepone;

public class Auth extends AppCompatActivity implements FragmentChanger {
    public  static final String STEP_ONE="step_one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        changeView(STEP_ONE);
    }

    @Override
    public void ChangeFragment(FragmentTitled fragment) {
        changeView(fragment);
    }

    private void changeView(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    private void changeView(String fragmentName){
        Fragment fragment;
        setTitle(fragmentName);
        switch (fragmentName){
            case STEP_ONE: fragment= stepone.newInstance();
            break;
            default:fragment= stepone.newInstance();
        }
        changeView(fragment);

    }

}
