package com.e.tremendocSDK.Api;

import com.e.tremendocSDK.BuildConfig;

public class URLS {

    private static final String IP = BuildConfig.DEBUG ?  "188.166.14.154" : "178.128.8.26";
   public static  final String SERVER = "http://" + IP + ":9000/tremendoc/api/";



    public static String SPECIALTY_DOCTORS = SERVER + "doctor/search/speciality/";
    public static String DOCTORS_SEARCH = SERVER + "doctor/search/name/";
    public static String DOCTORS_RANDOM = SERVER + "doctor/search/random";

    public static String SDK_AUTHENICATION =SERVER +"sdk-provider/authenticate";
    public static String SDK_CREATE_USER= SERVER + "sdk-provider/create";
}
