package com.e.tremendocSDK.Service.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Doctor {

    private String specialization;
    private String firstname;
    private String lastname;
    private String avatar;
    private int id;
    private double rating;
    private String hospital;
    private boolean available;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname(){
        return  firstname + "" + lastname;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static Doctor parse(JSONObject object)throws JSONException{
        Doctor doctor=new Doctor();
        doctor.setId(object.getInt("Id"));
        doctor.setFirstname(object.getString("firstname"));
        doctor.setLastname(object.getString("lastname"));
        doctor.setHospital(object.getString("hospital"));
        doctor.setAvatar(object.getString("image"));

        if (object.has("onlineStatus") && !object.isNull("onlineStatus")
                && object.getString("onlineStatus").equals("ONLINE")){
            doctor.setAvailable(true);
        }
        else doctor.setAvailable(false);
        doctor.setRating(object.getDouble("rating"));
        return doctor;
    }
}
