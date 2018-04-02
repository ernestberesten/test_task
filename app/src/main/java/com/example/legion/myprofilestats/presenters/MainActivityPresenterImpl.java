package com.example.legion.myprofilestats.presenters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.legion.myprofilestats.views.MainActivityView;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private Activity activity;
    private MainActivityView mainActivityView;

    private static final String CONNECT_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onCreate(Activity activity, MainActivityView view) {
        this.activity = activity;
        this.mainActivityView = view;
    }

    @Override
    public void startConnectionReceive() {
        activity.registerReceiver(connectionReceiver, new IntentFilter(CONNECT_ACTION));
    }


    private BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(CONNECT_ACTION)) {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = null;
                if (cm != null) {
                    activeNetwork = cm.getActiveNetworkInfo();
                }
                boolean isConnected = activeNetwork != null
                        && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    mainActivityView.showListMatchesFragment();
                    activity.unregisterReceiver(this);
                }
            }
        }
    };
}
