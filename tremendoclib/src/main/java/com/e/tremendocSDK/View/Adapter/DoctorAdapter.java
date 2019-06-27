package com.e.tremendocSDK.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahamed.multiviewadapter.ItemBinder;
import com.ahamed.multiviewadapter.ItemViewHolder;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.Service.Model.Doctor;
import com.squareup.picasso.Picasso;

public class DoctorAdapter extends ItemBinder<Doctor ,DoctorAdapter.DoctorVHolder> {

    public DoctorAdapter(){

    }
    @Override
    public DoctorAdapter.DoctorVHolder create(LayoutInflater inflater, ViewGroup parent) {
        View view= inflater.inflate(R.layout.holder_doctor,parent,false);
        return new DoctorVHolder(view);
    }

    @Override
    public void bind(DoctorAdapter.DoctorVHolder holder, Doctor item) {
        holder.doctorName.setText( "Dr. " + item.getFirstname() + item.getLastname());
        holder.hospitalName.setText(item.getHospital());
       Picasso.get().load(item.getAvatar()).into(holder.doctorImage);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof  Doctor;
    }

    @Override
    public int getSpanSize(int maxSpanCount) {
        return getSpanSize(maxSpanCount);
    }

    class DoctorVHolder extends ItemViewHolder<Doctor> {

        TextView doctorName, hospitalName;
        ImageView doctorImage;

        DoctorVHolder(View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.name);
            hospitalName= itemView.findViewById(R.id.hospital);
        }
    }
}
