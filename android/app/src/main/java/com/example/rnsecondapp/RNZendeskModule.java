package com.example.rnsecondapp;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.intercom.android.sdk.Intercom;

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
    public void initIntercom(String message){
        Log.e(TAG, "initIntercom: message = "+message);
        Toast.makeText(reactApplicationContext, "Message = "+message, Toast.LENGTH_SHORT).show();
        try {
            Intercom.client().logout();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intercom.client().registerUnidentifiedUser();
    }

    @ReactMethod
    public void displayMessenger(String message){
        Log.e(TAG, "displayMessenger: "+message);
        Toast.makeText(reactApplicationContext, "displayMessenger 222", Toast.LENGTH_SHORT).show();
//        Intercom.client().displayMessenger();

//        MessagingActivity.builder()
//                .withEngines(ChatEngine.engine())
//                .show(reactApplicationContext);

        Intent intent = new Intent(reactApplicationContext, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactApplicationContext.startActivity(intent);
    }

    @ReactMethod
    public void openZendesk(String name, String email, String phone){
        Log.e(TAG, "openZendesk: name = "+name);
        Log.e(TAG, "openZendesk: email = "+email);
        Log.e(TAG, "openZendesk: phone = "+phone);
        Toast.makeText(reactApplicationContext, "openZendesk 222", Toast.LENGTH_SHORT).show();
//        Intercom.client().displayMessenger();

//        MessagingActivity.builder()
//                .withEngines(ChatEngine.engine())
//                .show(reactApplicationContext);

        Intent intent = new Intent(reactApplicationContext, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.NAME,name);
        intent.putExtra(Constants.EMAIL,email);
        intent.putExtra(Constants.PHONE,phone);
        reactApplicationContext.startActivity(intent);
    }
}

//        Zendesk URL
//        Give this URL to your developers. It is used to connect to the app.
//        https://rnsecondapp.zendesk.com

//        App ID
//        Give this App ID to your developers. It is used to identify the app.
//        e311fb5e362c129cd72ae2b01553b7750cfff4c35218fb6f

//        Client ID
//        Give this Client ID to your developers. It is used to identify the app.
//        mobile_sdk_client_eb89305423a388b06436

//        Android code snippet
//        Give this code snippet to your developers. For security reasons it will be shown only once.
//        Zendesk.INSTANCE.init(context, "https://rnsecondapp.zendesk.com",
//        "e311fb5e362c129cd72ae2b01553b7750cfff4c35218fb6f",
//        "mobile_sdk_client_eb89305423a388b06436");
//        Support.INSTANCE.init(Zendesk.INSTANCE);

//        iOS code snippet
//        Give this code snippet to your developers. For security reasons it will be shown only once.
//        Objective-C
//        [ZDKZendesk initializeWithAppId: @"e311fb5e362c129cd72ae2b01553b7750cfff4c35218fb6f"
//        clientId: @"mobile_sdk_client_eb89305423a388b06436"
//        zendeskUrl: @"https://rnsecondapp.zendesk.com"];
//        [ZDKSupport initializeWithZendesk: [ZDKZendesk instance]];
//
//        Swift
//        Zendesk.initialize(appId: "e311fb5e362c129cd72ae2b01553b7750cfff4c35218fb6f",
//        clientId: "mobile_sdk_client_eb89305423a388b06436",
//        zendeskUrl: "https://rnsecondapp.zendesk.com")
//        Support.initialize(withZendesk: Zendesk.instance)


