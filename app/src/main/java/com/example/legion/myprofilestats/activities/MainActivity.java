package com.example.legion.myprofilestats.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.legion.myprofilestats.fragments.AllMatchesFragment;
import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.fragments.FragmentMoreStats;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.utils.InternetConnectionReceiver;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showListMatchesFragment();
    }

    public void showListMatchesFragment() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.container, AllMatchesFragment.newInstance());
        mFragmentTransaction.commitAllowingStateLoss();
    }

    public void showMoreInfoFragment(OpenDotaMatchesModel matchModel) {
        FragmentMoreStats fragmentMoreStats = FragmentMoreStats.newInstance();
        fragmentMoreStats.setModel(matchModel);
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.container, fragmentMoreStats);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
