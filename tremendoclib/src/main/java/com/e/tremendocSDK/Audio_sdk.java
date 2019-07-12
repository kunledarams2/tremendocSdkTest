package com.e.tremendocSDK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.format.Formatter;
import android.util.Patterns;
import android.widget.Toast;

import com.e.tremendocSDK.View.UI.Activity.Authm;

import java.util.regex.Pattern;

public class Audio_sdk {

    private Context context;

    public Audio_sdk(Context context) {
        this.context = context;
    }

    public  void setupSDK(String userEmail){

        if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){

            Intent intent = new Intent(context, Authm.class);
            intent.putExtra("userEmail",userEmail);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        else Toast.makeText(context,"Wrong Email",Toast.LENGTH_LONG).show();
    }
}
