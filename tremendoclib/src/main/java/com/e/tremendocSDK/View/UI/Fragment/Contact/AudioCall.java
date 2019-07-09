package com.e.tremendocSDK.View.UI.Fragment.Contact;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.UI.Activity.ContactActivity;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link AudioCall#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioCall extends FragmentTitled {


    public AudioCall() {
        // Required empty public constructor
    }

    public static AudioCall newInstance() {
        AudioCall fragment = new AudioCall();
        fragment.setTitle(ContactActivity.VOICE_CALL);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_call, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }



}
