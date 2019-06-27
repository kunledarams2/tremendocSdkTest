package com.e.tremendocSDK.Api;

import android.content.Context;

import java.util.Map;

public class API {
    private static API mApi;
   public static Context context;



    public API(Context context) {
       this.context= context;
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
}
