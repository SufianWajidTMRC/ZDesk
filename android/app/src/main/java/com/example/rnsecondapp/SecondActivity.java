package com.example.rnsecondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import zendesk.chat.Chat;
import zendesk.chat.ChatConfiguration;
import zendesk.chat.ChatEngine;
import zendesk.chat.ChatProvidersConfiguration;
import zendesk.chat.PreChatFormFieldStatus;
import zendesk.chat.VisitorInfo;
import zendesk.messaging.MessagingActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private Context context;
    boolean startedChat = false;
    String name = null;
    String email = null;
    String phone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
//        setContentView(R.layout.activity_second);
        context = this;
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra(Constants.NAME);
            email = intent.getStringExtra(Constants.EMAIL);
            phone = intent.getStringExtra(Constants.PHONE);
        }
        if (name == null) {
            name = "";
        }
        if (email == null) {
            email = "";
        }
        if (phone == null) {
            phone = "";
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                try {
                    Chat.INSTANCE.init(context, "db3P4mXRizfzjfqb4fczfOM0Uao3Teru");
//                    Chat.INSTANCE.resetIdentity();
////                    The chat can also be configured using either the ProfileProvider or the ChatProvider class.
//
//                    ProfileProvider profileProvider = Chat.INSTANCE.providers().profileProvider();
//                    ChatProvider chatProvider = Chat.INSTANCE.providers().chatProvider();
//
//                    VisitorInfo visitorInfo = VisitorInfo.builder()
//                            .withName("Bob")
//                            .withEmail("bob@example.com")
//                            .withPhoneNumber("123456") // numeric string
//                            .build();
//                    profileProvider.setVisitorInfo(visitorInfo, null);

                    VisitorInfo visitorInfo = VisitorInfo.builder()
//                            .withName("Bob")
//                            .withEmail("bob@example.com")
//                            .withPhoneNumber("123456999") // numeric string
                            .withName(name)
                            .withEmail(email)
                            .withPhoneNumber(phone) // numeric string
                            .build();

                    boolean showPreChatForm = false;
                    if (name.contentEquals("") || email.contentEquals("")){
                        showPreChatForm = true;
                    }


                    ChatConfiguration chatConfig = ChatConfiguration.builder()
	                    //    .withAgentAvailabilityEnabled(false)
//                            .withPreChatFormEnabled(false)
                            .withEmailFieldStatus(PreChatFormFieldStatus.REQUIRED)
                            .withNameFieldStatus(PreChatFormFieldStatus.REQUIRED)
                            .withPhoneFieldStatus(PreChatFormFieldStatus.OPTIONAL)
                            .build();
//                    List<Configuration> configurationList = chatConfig.getConfigurations();
//                    for (Configuration cc:
//                         configurationList) {
//                        Log.e(TAG, "onCreate: "+cc.);
//                    }

                    ChatProvidersConfiguration chatProvidersConfiguration = ChatProvidersConfiguration.builder()
                            .withVisitorInfo(visitorInfo)
                            .withDepartment("Support")
                            .build();

                    Log.e(TAG, "onCreate: name = "+visitorInfo.getName());
                    Log.e(TAG, "onCreate: email = "+visitorInfo.getEmail());
                    Log.e(TAG, "onCreate: phone = "+visitorInfo.getPhoneNumber());
                    Log.e(TAG, "onCreate: Department = "+chatProvidersConfiguration.getDepartmentName());
//                    Log.e(TAG, "onCreate: Vis Info = "+chatProvidersConfiguration.getVisitorInfo());

                    Chat.INSTANCE.setChatProvidersConfiguration(chatProvidersConfiguration);

                    MessagingActivity.builder()
                            .withEngines(ChatEngine.engine())
                            .show(context, chatConfig);
                    startedChat = true;

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "run: ERRORR..............");
                }

            }
        }, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        if (startedChat) {
            Chat.INSTANCE.resetIdentity();
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }
}