package com.e.tremendocSDK.View.UI.Fragment.Signup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Activity.Finddoctor;
import com.e.tremendocSDK.View.UI.Activity.Signup;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
// * {@link stepone} interface
 * to handle interaction events.
 * Use the {@link stepone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stepone extends FragmentTitled implements FragmentChanger, View.OnClickListener {
    Button talktodocBtn;

    public stepone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment stepone.
     */
    // TODO: Rename and change types and number of parameters
    public static stepone newInstance() {
        stepone fragment = new stepone();
        fragment.setTitle(Signup.STEP_ONE);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_stepone, container, false);
        talktodocBtn=view.findViewById(R.id.signup_btn);
        talktodocBtn.setOnClickListener(this);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {

    }



    @Override
    public void ChangeFragment(FragmentTitled fragment) {

    }

    @Override
    public void onClick(View view) {

        if(view==talktodocBtn){
            Process();
        }
    }

    private void Process(){
        Intent intent= new Intent(getContext(), Finddoctor.class);
        startActivity(intent);
    }



}
