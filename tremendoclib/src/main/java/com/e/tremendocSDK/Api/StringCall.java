package com.e.tremendocSDK.Api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class StringCall {

    Context context;
    API mapi;

    public StringCall(Context context) {
        this.context = context;
        this.mapi=API.getInstance(context);
    }



    public void get(String url, Map params, Response.Listener<String>listener, Response.ErrorListener errorListener){

        String tag = mapi.getTag(url, "get");
        if(params !=null){
            url =mapi.buildparam(url,params);
        }

        Response.Listener<String>resListener=HandeleAuth(listener);

        StringRequest request=new StringRequest(Request.Method.GET, url, resListener,errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
    }


    private  Response.Listener<String>HandeleAuth(final Response.Listener listener){
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.has("code") && obj.getInt("code") == 40) {
                        Toast.makeText(context, "Sorry you need to sign again", Toast.LENGTH_LONG).show();

                    }
                    else listener.onResponse(response);
                } catch (JSONException e) {

                    Log.e( "JSON auth error",e.getMessage());
                }
            }
        };
    }

}
