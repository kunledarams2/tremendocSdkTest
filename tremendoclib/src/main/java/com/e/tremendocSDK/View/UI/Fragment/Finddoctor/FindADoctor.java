package com.e.tremendocSDK.View.UI.Fragment.Finddoctor;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.tremendocSDK.Binder.Doctorbinder;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.View.Callback.FragmentChanger;
import com.e.tremendocSDK.View.UI.Activity.Finddoctor;
import com.e.tremendocSDK.View.UI.Fragment.FragmentTitled;
import com.e.tremendocSDK.ViewModel.DoctorViewmodel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link FindADoctor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindADoctor extends FragmentTitled implements FragmentChanger {
    // TODO: Rename parameter arguments, choose names that match

   private RecyclerView recyclerView;
   private ImageButton searchBtn;
//   private LinearLayout linearLayout;
    private RelativeLayout trylayout;
    private Button retrybtn;
   private  ProgressBar loader;
   private LinearLayoutManager llm;
   private Doctorbinder doctorbinder;
   private DoctorViewmodel viewmodel;
   private EditText searchField;
   private int page ;

    public FindADoctor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FindADoctor.
     */
    // TODO: Rename and change types and number of parameters
    public static FindADoctor newInstance() {
        FindADoctor fragment = new FindADoctor();
        fragment.setTitle(Finddoctor.CHAT_WITH_DOCTOR);
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
        return view;
    }


    private void setupView(View view){
        recyclerView=view.findViewById(R.id.recycler_view);
        loader = view.findViewById(R.id.progressBar);
        searchField = view.findViewById(R.id.search_field);
        searchBtn = view.findViewById(R.id.search_btn);
        trylayout=view.findViewById(R.id.tryLayout);
        retrybtn= view.findViewById(R.id.retryBtn);

        retrybtn.setOnClickListener(view1 -> retrySearch());

        searchBtn.setOnClickListener(btn->{
            String query = searchField.getText().toString();
            page =1;
            trylayout.setVisibility(View.GONE);
            viewmodel.fetchSearchDoctor(query,page);

        });





    }

    private void retrySearch() {
    }

    private void setupAdapter(){
        llm= new LinearLayoutManager(getActivity());


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {

    }


    @Override
    public void ChangeFragment(FragmentTitled fragment) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(URLS uri);
//    }
}
