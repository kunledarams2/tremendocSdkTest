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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.ahamed.multiviewadapter.SimpleRecyclerAdapter;
import com.e.tremendocSDK.Binder.Doctorbinder;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.Service.Model.Doctor;

import com.e.tremendocSDK.View.Adapter.DocAdapter;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Activity.Finddoctor;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;
import com.e.tremendocSDK.ViewModel.DoctorViewmodel;

import java.util.ArrayList;
import java.util.List;


public class FindADoctor extends FragmentTitled implements FragmentChanger {
    // TODO: Rename parameter arguments, choose names that match

   private RecyclerView recyclerView;
   private ImageButton searchBtn;
//   private LinearLayout linearLayout;
    private RelativeLayout retrylayout;
    private Button retrybtn;
   private  ProgressBar loader;
   private LinearLayoutManager llm;
   private Doctorbinder doctorbinder;
   private DoctorViewmodel viewmodel;
   private EditText searchField;
   private TextView errormessage;
   private ImageView emptyIcon;
   private int page;
   private int specailtyId, doctorId;
   private List<Doctor>doctor=new ArrayList<>();
   private DocAdapter docAdapter;
//    private SimpleRecyclerAdapter<Doctor, Doctorbinder> adapter;
//    private SimpleRecyclerAdapter<Doctor, Doctorbinder> adapter;

    public FindADoctor() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FindADoctor newInstance(int specailtyId) {
        FindADoctor fragment = new FindADoctor();
        fragment.specailtyId = specailtyId;

//        fragment.setTitle(Finddoctor.CHAT_WITH_DOCTOR);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewmodel = ViewModelProviders.of(this).get(DoctorViewmodel.class);
        observe(viewmodel);
        viewmodel.fetchSpecialyDoctor(specailtyId,page);

    }

    private void observe(DoctorViewmodel viewmodel) {
        viewmodel.getMediatorLiveData().observe(this,doctorResult -> {
            if(doctorResult.isSuccessful() && doctorResult.getDatalist().isEmpty()){
                recyclerView.setVisibility(View.GONE);
                retrylayout.setVisibility(View.VISIBLE);
            }
           else if(doctorResult.isSuccessful() && !doctorResult.getDatalist().isEmpty()){
               recyclerView.setVisibility(View.VISIBLE);
               retrylayout.setVisibility(View.GONE);
                docAdapter=new DocAdapter(getContext(),doctorResult.getDatalist());

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

    private void setupAdapter(){
        llm= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(docAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

//        if(doctor.size()>0){
//            docAdapter=new DocAdapter(getContext(), doctor );
//
//            );
//        }
        Doctorbinder doctorbinder= new Doctorbinder();

//        adapter=new SimpleRecyclerAdapter<>(doctorbinder);

//        recyclerView.setAdapter(adapter);




    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {

    }


    @Override
    public void ChangeFragment(FragmentTitled fragment) {

    }


}
