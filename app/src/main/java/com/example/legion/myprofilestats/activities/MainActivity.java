package com.example.legion.myprofilestats.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.legion.myprofilestats.fragments.AllMatchesFragment;
import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.fragments.FragmentMoreStats;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.presenters.MainActivityPresenter;
import com.example.legion.myprofilestats.presenters.MainActivityPresenterImpl;
import com.example.legion.myprofilestats.views.MainActivityView;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter presenter;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenterImpl();
        showListMatchesFragment();
    }

    @Override
    public void showListMatchesFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, AllMatchesFragment.newInstance());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void showMoreInfoFragment(OpenDotaMatchesModel matchModel) {
        FragmentMoreStats fragmentMoreStats = FragmentMoreStats.newInstance();
        fragmentMoreStats.setModel(matchModel);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragmentMoreStats);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public MainActivityPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
