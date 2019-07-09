package com.e.tremendocSDK.View.UI.Fragment.Finddoctor;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.tremendocSDK.R;
import com.e.tremendocSDK.Service.Model.Doctor;

import com.e.tremendocSDK.View.Adapter.DocAdapter;
import com.e.tremendocSDK.View.Callback.FragmentChanger;

import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;
import com.e.tremendocSDK.ViewModel.DoctorViewmodel;



public class FindADoctor extends FragmentTitled implements FragmentChanger {

   private RecyclerView recyclerView;
   private ImageButton searchBtn;
    private RelativeLayout retrylayout;
    private Button retrybtn;
   private  ProgressBar loader;
   private LinearLayoutManager llm;

   private DoctorViewmodel viewmodel;
   private EditText searchField;
   private TextView errormessage;
   private ImageView emptyIcon;
   private int page= 1;
   private int specialtyId, doctorId;
   private DocAdapter docAdapter;


    public FindADoctor() {
        // Required empty public constructor
    }


    public static FindADoctor newInstance(int specailtyId) {
        FindADoctor fragment = new FindADoctor();
        fragment.specialtyId = specailtyId;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_findadoctor, container, false);
        setupView(view);
        setupAdapter();
        return view;
    }


    private void setupView(View view){
        recyclerView=view.findViewById(R.id.recycler_view);
        loader = view.findViewById(R.id.progressBar);
        searchField = view.findViewById(R.id.search_field);
        searchBtn = view.findViewById(R.id.search_btn);
        retrylayout=view.findViewById(R.id.tryLayout);
        retrybtn= view.findViewById(R.id.retryBtn);
        errormessage=view.findViewById(R.id.placeholder_label);
        emptyIcon=view.findViewById(R.id.placeholder_icon);
//        doctor =new Doctor();

        retrybtn.setOnClickListener(view1 -> retrySearch());

        searchBtn.setOnClickListener(btn->{
            String query = searchField.getText().toString();
            page =1;
            if(!TextUtils.isEmpty(query)){
                retrylayout.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);
                viewmodel.fetchSearchDoctor(query,page);
            }

        });
    }

    private void setupAdapter(){
        llm= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        docAdapter =new DocAdapter();
        recyclerView.setAdapter(docAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

//        if(doctor.size()>0){
//            docAdapter=new DocAdapter(getContext(), doctor );
//
//            );
//        }
//        Doctorbinder doctorbinder= new Doctorbinder();

//        adapter=new SimpleRecyclerAdapter<>(doctorbinder);

//        recyclerView.setAdapter(adapter);




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewmodel = ViewModelProviders.of(this).get(DoctorViewmodel.class);
        observe(viewmodel);
        viewmodel.fetchSpecialyDoctor(specialtyId,page);

    }

    private void observe(DoctorViewmodel viewmodel) {
//        loader.setVisibility(View.GONE);
        viewmodel.getMediatorLiveData().observe(this,doctorResult -> {
            loader.setVisibility(View.GONE);
            if(doctorResult.isSuccessful() && doctorResult.getDatalist().isEmpty()){
                recyclerView.setVisibility(View.GONE);
                errormessage.setText("No matching doctors found");
                retrylayout.setVisibility(View.VISIBLE);
            }
           else if(doctorResult.isSuccessful() && !doctorResult.getDatalist().isEmpty()){
               recyclerView.setVisibility(View.VISIBLE);
               retrylayout.setVisibility(View.GONE);
                Toast.makeText(getContext(), String.valueOf(doctorResult.getDatalist().size()),Toast.LENGTH_LONG).show();
               docAdapter.setDoctors(doctorResult.getDatalist());
//                docAdapter=new DocAdapter(getContext(),doctorResult.getDatalist());

            } else if(!doctorResult.isSuccessful()){
               recyclerView.setVisibility(View.GONE);
                retrylayout.setVisibility(View.VISIBLE);
                errormessage.setText(doctorResult.getMessage());
                emptyIcon.setImageResource(R.drawable.placeholder_error);

            }
        });
    }

    private void retrySearch() {
    }


    public void onButtonPressed() {

    }


    @Override
    public void ChangeFragment(FragmentTitled fragment) {

    }


}
