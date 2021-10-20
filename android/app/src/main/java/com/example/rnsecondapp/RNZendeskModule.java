package com.example.rnsecondapp;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class RNZendeskModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactApplicationContext;
    private static final String TAG = "RNZendeskModule";

    public RNZendeskModule(ReactApplicationContext context){
        super(context);
        reactApplicationContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "RNZendeskModule";
    }


    @ReactMethod
    public void displayMessenger(String message){
        Log.e(TAG, "displayMessenger: "+message);
        Toast.makeText(reactApplicationContext, "test zendesk without opening second activity", Toast.LENGTH_SHORT).show();
//        MessagingActivity.builder()
//                .withEngines(ChatEngine.engine())
//                .show(reactApplicationContext);
    }

    @ReactMethod
    public void openZendesk(String name, String email, String phone){
        Log.e(TAG, "openZendesk: name = "+name);
        Log.e(TAG, "openZendesk: email = "+email);
        Log.e(TAG, "openZendesk: phone = "+phone);
        Toast.makeText(reactApplicationContext, "openZendesk 222", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(reactApplicationContext, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.NAME,name);
        intent.putExtra(Constants.EMAIL,email);
        intent.putExtra(Constants.PHONE,phone);
        reactApplicationContext.startActivity(intent);
    }
}