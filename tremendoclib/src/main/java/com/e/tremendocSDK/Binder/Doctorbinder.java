package com.e.tremendocSDK.Binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahamed.multiviewadapter.ItemBinder;
import com.ahamed.multiviewadapter.ItemViewHolder;
import com.e.tremendocSDK.R;
import com.e.tremendocSDK.Service.Model.Doctor;
import com.squareup.picasso.Picasso;

public class Doctorbinder extends ItemBinder<Doctor, Doctorbinder.DoctorHolder> {


    @Override
    public Doctorbinder.DoctorHolder create(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.holder_doctor,parent,false);

        return new DoctorHolder(view);
    }

    @Override
    public void bind(Doctorbinder.DoctorHolder holder, Doctor item) {
        holder.bind(item);

    }

    @Override
    public boolean canBindData(Object item) {
        return false;
    }

    class DoctorHolder extends  ItemViewHolder<Doctor> {

        TextView name, hospital;
        ImageButton  chatBtn;
        ImageView avatar;
        View view, status;
        LinearLayout ratingView;
        public DoctorHolder(View itemView) {
            super(itemView);

            name = view.findViewById(R.id.name);
            hospital = view.findViewById(R.id.hospital);
            avatar = view.findViewById(R.id.avatar);
            chatBtn = view.findViewById(R.id.btn_chat);
            status = view.findViewById(R.id.available);
            ratingView = view.findViewById(R.id.rating);
        }

        void bind(final Doctor doctor)
        {
            name.setText("Dr. " + doctor.getFullname());
            hospital.setText(doctor.getHospital());
            status.setBackgroundResource(doctor.isAvailable() ? R.drawable.circle_green : R.drawable.circle_red);
            setRating(ratingView, doctor.getRating());

            Picasso.get().load(doctor.getAvatar()).into(avatar);

//            if (callBtnListener != null) {
//
//                videoBtn.setOnClickListener(btn -> {
//                    IO.setData(btn.getContext(), DOCTOR_NAME, doctor.getFullName());
//                    callBtnListener.onVideoClicked(doctor.getId());
//                });
//                voiceBtn.setOnClickListener(btn -> {
//                    IO.setData(btn.getContext(), DOCTOR_NAME, doctor.getFullName());
//                    IO.setData(btn.getContext(), DOCTOR_AVATAR, doctor.getAvatar());
//                    callBtnListener.onVoiceClicked(doctor.getId());
//                });
//                chatBtn.setOnClickListener(btn -> {
//                    IO.setData(btn.getContext(), DOCTOR_NAME, doctor.getFullName());
//                    callBtnListener.onChatClicked(doctor.getId());
//                });
//            }

        }

        private void setRating(LinearLayout view, double rating)
        {
            view.removeAllViews();
            int intVal = (int) rating;
            int absentVal = 5 - intVal;
            double rem = rating - intVal;

            for (int i = 0; i < intVal; i++) {
                ImageView img = new ImageView(view.getContext());
                img.setImageResource(R.drawable.ic_star);
                view.addView(img);
            }
            if (rem > 0) {
                ImageView img = new ImageView(view.getContext());
                img.setImageResource(R.drawable.ic_star_half);
                view.addView(img);

                absentVal--; //very important;
            }

            for (int i = 0; i < absentVal; i++) {
                ImageView img = new ImageView(view.getContext());
                img.setImageResource(R.drawable.ic_star_empty);
                view.addView(img);
            }
        }
    }
}
