package com.example.legion.myprofilestats.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.legion.myprofilestats.MatchesApplication;
import com.example.legion.myprofilestats.activities.MainActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by Ernest on 07.03.2018.
 */

public class InternetConnectionReceiver extends BroadcastReceiver {

    private MainActivity mainActivity;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            mainActivity.showListMatchesFragment();
        }
    }

    public void attachActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
