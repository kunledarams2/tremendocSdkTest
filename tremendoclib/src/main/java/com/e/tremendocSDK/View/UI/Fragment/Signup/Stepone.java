package com.e.tremendocSDK.View.UI.Fragment.Signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Activity.Signup;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;
import com.e.tremendocSDK.View.UI.UUitil.IO;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
// * {@link Stepone} interface
 * to handle interaction events.
 * Use the {@link Stepone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stepone extends FragmentTitled implements  View.OnClickListener {
   private Button continueBtn;
   private EditText usernameField, password,passwordconfirm;
   private TextView indicator;
   private ImageButton reviewbtn;
   private boolean visible=false;
   private FragmentChanger fragmentChanger;

   public static final String  USERNAME= "sign_up_username";
   public static final String  PASSWORD = "sign_up_password";

    public Stepone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Stepone.
     */
    // TODO: Rename and change types and number of parameters
    public static Stepone newInstance() {
        Stepone fragment = new Stepone();
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
        setView(view);
        //

        return view;

    }

    private void setView(View view){

        usernameField= view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        passwordconfirm=view.findViewById(R.id.password_confirm);
        indicator= view.findViewById(R.id.indicator);
        reviewbtn=view.findViewById(R.id.reveal_btn);

        continueBtn=view.findViewById(R.id.next_btn);
        continueBtn.setOnClickListener(this);

        reviewbtn.setOnClickListener(view1 -> {
            if(visible){
                password.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password.setSelection(password.length());

                visible=false;
                reviewbtn.setImageResource(R.drawable.ic_eye_black);
            }

            else {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                password.setSelection(password.length());
                visible=true;
                reviewbtn.setImageResource(R.drawable.ic_eye_green);
            }
        });

        passwordconfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String confirm= editable.toString();
                String passwordField= password.getText().toString();

                if(!TextUtils.isEmpty(passwordField) && !TextUtils.isEmpty(confirm)){
                    if(passwordField.equals(confirm)){
                        indicator.setBackgroundResource(R.drawable.ic_check_green_small);
                    }
                    else {
                        indicator.setBackgroundResource(R.drawable.ic_close_red_small);
                    }
                }

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {

    }


    @Override
    public void onClick(View view) {

        if(view==continueBtn){
            Process();
        }
    }



    private void Process(){


        String username = usernameField.getText().toString();
        String password1 = password.getText().toString();
        String password2 = passwordconfirm.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getContext(), "Username is required", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(getContext(), "Password is required", Toast.LENGTH_LONG).show();
        } else if (password1.length() < 6) {
            Toast.makeText(getContext(), "Password should be a minimum of 6 characters", Toast.LENGTH_LONG).show();
        } else if (!password1.equals(password2)) {
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
        }
        if(!TextUtils.isEmpty(username) & !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)){

            IO.setData(getContext(),USERNAME,username);
            IO.setData(getContext(),PASSWORD,password1);

            fragmentChanger.ChangeFragment(Steptwo.newInstance());

        }
//        Intent intent= new Intent(getContext(), Finddoctor.class);
//        startActivity(intent);
    }



}
