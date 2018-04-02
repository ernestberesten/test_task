package com.example.legion.myprofilestats.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.legion.myprofilestats.MatchesApplication;
import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.adapters.MatchesListAdapter;
import com.example.legion.myprofilestats.models.HeroNamesModel;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.presenters.AllMatchesPresenter;
import com.example.legion.myprofilestats.presenters.AllMatchesPresenterImpl;
import com.example.legion.myprofilestats.presenters.MainActivityPresenter;
import com.example.legion.myprofilestats.views.AllMatchesView;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link BaseFragment} subclass.
 */
public class AllMatchesFragment extends BaseFragment implements AllMatchesView {

    private TextView textNoConnection;

    public AllMatchesFragment() {
    }

    public static AllMatchesFragment newInstance() {
        return new AllMatchesFragment();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_all_matches, null);
        textNoConnection = findViewById(R.id.text_no_connection);
        RecyclerView recyclerView = findViewById(R.id.matches_recycler_view);
        AllMatchesPresenter presenter = new AllMatchesPresenterImpl(mainActivity, this, recyclerView);
        presenter.getData();
    }

    @Override
    public void showTextConnection() {
        textNoConnection.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTextConnection() {
        textNoConnection.setVisibility(View.GONE);
    }

    @Override
    public MainActivityPresenter getPresenter() {
        return mainActivity.getPresenter();
    }
}
