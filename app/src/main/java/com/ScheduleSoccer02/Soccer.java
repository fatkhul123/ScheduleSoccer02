package com.ScheduleSoccer02;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

class Soccer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
