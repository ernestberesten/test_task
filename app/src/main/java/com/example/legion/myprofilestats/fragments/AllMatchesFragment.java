package com.example.legion.myprofilestats.fragments;


import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.legion.myprofilestats.MatchesApplication;
import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.adapters.MatchesListAdapter;
import com.example.legion.myprofilestats.dao.MatchesDao;
import com.example.legion.myprofilestats.models.HeroNamesModel;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.utils.InternetConnectionReceiver;

import java.io.IOException;
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
public class AllMatchesFragment extends BaseFragment {

    private List<OpenDotaMatchesModel> matchesData;
    private TextView textNoConnection;
    private InternetConnectionReceiver receiver;

    public AllMatchesFragment() {
        // Required empty public constructor
    }

    public static AllMatchesFragment newInstance() {
        return new AllMatchesFragment();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        mCurrentView = inflater.inflate(R.layout.fragment_all_matches, null);
        textNoConnection = findViewById(R.id.text_no_connection);
        matchesData = MatchesApplication.getMatchesDataBase().getMatchesDao().getAllMatches();
        if (matchesData != null && matchesData.size() > 0 && receiver != null) {
            mMainActivity.unregisterReceiver(receiver);
        }
        getData();
    }

    private void getData() {
        MatchesApplication.getApi().getData().enqueue(new Callback<List<OpenDotaMatchesModel>>() {
            @Override
            public void onResponse(Call<List<OpenDotaMatchesModel>> call, Response<List<OpenDotaMatchesModel>> response) {
                textNoConnection.setVisibility(View.GONE);
                if (response.body() != null) {
                    setData(response.body());
                } else {
                    if (matchesData != null && matchesData.size() > 0) {
                        initMatchesRecycler(matchesData);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OpenDotaMatchesModel>> call, Throwable t) {
                if (matchesData != null && matchesData.size() > 0) {
                    initMatchesRecycler(matchesData);
                } else {
                    textNoConnection.setVisibility(View.VISIBLE);
                    receiver = new InternetConnectionReceiver();
                    receiver.attachActivity(mMainActivity);
                    mMainActivity.registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        });
    }

    private void setData(List<OpenDotaMatchesModel> body) {
        final List<OpenDotaMatchesModel> openDotaMatchesModels = body;
        new ThreadPoolExecutor(4, 8, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()).execute(new Runnable() {
            @Override
            public void run() {
                getHeroNames(openDotaMatchesModels);

            }
        });
    }

    private void getHeroNames(final List<OpenDotaMatchesModel> openDotaMatchesModels) {
        MatchesApplication.getApi().getHeroName().enqueue(new Callback<List<HeroNamesModel>>() {
            @Override
            public void onResponse(Call<List<HeroNamesModel>> call, Response<List<HeroNamesModel>> response) {
                if (response.body() != null) {
                    setHeroNames(openDotaMatchesModels, response.body());
                }
            }

            @Override
            public void onFailure(Call<List<HeroNamesModel>> call, Throwable t) {
                initMatchesRecycler(openDotaMatchesModels);
            }
        });
    }

    private void setHeroNames(List<OpenDotaMatchesModel> openDotaMatchesModels, List<HeroNamesModel> body) {
        for (OpenDotaMatchesModel model : openDotaMatchesModels) {
            for (HeroNamesModel heroNamesModel : body) {
                if (model.getHeroId() == heroNamesModel.getHeroId()) {
                    model.setHeroName(heroNamesModel.getHeroName());
                }
            }
        }
        if (matchesData != null && matchesData.size() > 0) {
            MatchesApplication.getMatchesDataBase().getMatchesDao().updateAll(openDotaMatchesModels);
        } else {
            MatchesApplication.getMatchesDataBase().getMatchesDao().insertAll(openDotaMatchesModels);
        }
        initMatchesRecycler(openDotaMatchesModels);
    }

    private void initMatchesRecycler(List<OpenDotaMatchesModel> body) {
        RecyclerView recyclerView = findViewById(R.id.matches_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MatchesListAdapter matchesListAdapter = new MatchesListAdapter(mMainActivity, body);
        recyclerView.setAdapter(matchesListAdapter);
    }

}
