package com.example.legion.myprofilestats.presenters;

import android.app.Activity;

import com.example.legion.myprofilestats.views.MainActivityView;

public interface MainActivityPresenter {
    void onCreate(Activity activity, MainActivityView view);

    void startConnectionReceive();
}
