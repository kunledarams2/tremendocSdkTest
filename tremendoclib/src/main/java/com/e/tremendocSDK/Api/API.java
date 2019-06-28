package com.e.tremendocSDK.Api;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class API {
    private static API mApi;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
   public static Context context;


    public static final String PUSH_TOKEN_SET = "PUSH_TOKEN_SET";

    public static final String SHARED_PREFERENCES = "com.tremendoc.tremendoc.SHARED_PREFERENCES";
    public static final String EMAIL = "email";
    public static final String SESSION_ID = "sessionId";
    public static final String USERNAME = "username";
    public static final String CUSTOMER_ID = "customerId";
    public static final String USER_DATA = "user_data";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHONE_NUMBER = "";
    public static final String AVATAR = "avatar";

    public static final String MY_UUID = "MY_UUID";





    public API(Context context) {

        this.context= context;
        this.requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);

            }
        });
    }

    private RequestQueue getRequestQueue() {

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context);
        }

        return requestQueue;
    }

    public static synchronized API getInstance(Context context){
        if(mApi==null){
            mApi=new API(context);

        }
        return mApi;
    }

    public  static  String buildparam(String url, Map<String, String>params){
        if(params.size()>0){
            StringBuilder builder= new StringBuilder(url);
            builder.append("?");

            for(String key : params.keySet()){
                builder.append(key);
                builder.append("=");
                builder.append(params.get(key));
                builder.append("&");

            }
            builder.deleteCharAt(params.size()-1);
            return builder.toString();
        }
        return url;
    }
    public String getTag(String url, String method){

        url=url.replace(URLS.SERVER, "");
        String tag = url.replace("/","-");
        tag= tag +method;

        return tag;
    }

    private static Map<String, String> jsonToMap(String jsonString) {
        Map<String, String> map = new HashMap<>();
        try {
            JSONObject obj = new JSONObject(jsonString);
            if (obj.has(EMAIL))
                map.put(EMAIL, obj.getString(EMAIL));

            if (obj.has(SESSION_ID))
                map.put(SESSION_ID, obj.getString(SESSION_ID));

            if (obj.has(CUSTOMER_ID))
                map.put(CUSTOMER_ID, obj.getString(CUSTOMER_ID));

            if (obj.has(USERNAME))
                map.put(USERNAME, obj.getString(USERNAME));

            if (obj.has(FIRST_NAME))
                map.put(FIRST_NAME, obj.getString(FIRST_NAME));

            if (obj.has(LAST_NAME))
                map.put(LAST_NAME, obj.getString(LAST_NAME));
        }catch (JSONException e){

        }
        return map;
    }

    public static void setCredentials(Context context, String json) {
        setCredentials(context, jsonToMap(json));
    }

    public static void setCredentials(Context context, Map<String, String> credentials){
        SharedPreferences.Editor editor = context.getSharedPreferences(API.SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putString(API.SESSION_ID, credentials.get(API.SESSION_ID));
        editor.putString(API.EMAIL, credentials.get(API.EMAIL));
        editor.putString(API.CUSTOMER_ID, credentials.get(API.CUSTOMER_ID));
        editor.putString(API.USERNAME, credentials.get(API.USERNAME));
        editor.putString(FIRST_NAME, credentials.get(FIRST_NAME));
        editor.putString(LAST_NAME, credentials.get(LAST_NAME));
        editor.apply();
    }

    public static Map<String, String> getCredentials(Context context){
        Map<String, String> credentials = new HashMap<>();
        SharedPreferences prefs = context.getSharedPreferences(API.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String email = prefs.getString(API.EMAIL,"");
        String sessionId = prefs.getString(API.SESSION_ID,"");
        String username = prefs.getString(API.USERNAME, "");
        String customerId = prefs.getString(API.CUSTOMER_ID, "");

        credentials.put(API.EMAIL, email);
        credentials.put(API.SESSION_ID, sessionId);
        credentials.put(API.USERNAME, username);
        credentials.put(API.CUSTOMER_ID, customerId);
        credentials.put(FIRST_NAME, prefs.getString(FIRST_NAME, ""));
        credentials.put(LAST_NAME, prefs.getString(LAST_NAME, ""));
        return credentials;
    }
}
