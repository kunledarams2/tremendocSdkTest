package com.e.tremendocSDK.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.Service.Model.Doctor;
import com.e.tremendocSDK.View.UI.Activity.DirectRouting;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DoctorViewHolder> {

    Context context;
    List<Doctor> doctors=new ArrayList<>();
//
    public DocAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public DocAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.holder_doctor,parent,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocAdapter.DoctorViewHolder holder, int position) {

        holder.doctorName.setText(doctors.get(position).getFirstname());
        holder.hospitalName.setText(doctors.get(position).getHospital());

        Picasso.get().load(doctors.get(position).getAvatar())
                .into(holder.doctorAvatar);


        holder.voiceCall.setOnClickListener(btn->{

            Intent intent= new Intent(context, DirectRouting.class);
            intent.putExtra("doctorId",doctors.get(position).getId());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public void setDoctors(List<Doctor> doctors){
        this.doctors=doctors;
    }


    class DoctorViewHolder extends RecyclerView.ViewHolder {
        ImageView doctorAvatar;
        TextView doctorName, hospitalName;
        ImageButton voiceCall;

        DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorAvatar =itemView.findViewById(R.id.avatar);
            hospitalName =itemView.findViewById(R.id.hospital);
            doctorName =itemView.findViewById(R.id.name);

            voiceCall=itemView.findViewById(R.id.btn_voice_call);

        }
    }
}
