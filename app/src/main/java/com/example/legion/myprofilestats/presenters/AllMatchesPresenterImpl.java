package com.example.legion.myprofilestats.presenters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.legion.myprofilestats.MatchesApplication;
import com.example.legion.myprofilestats.activities.MainActivity;
import com.example.legion.myprofilestats.adapters.MatchesListAdapter;
import com.example.legion.myprofilestats.fragments.AllMatchesFragment;
import com.example.legion.myprofilestats.models.HeroNamesModel;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.views.AllMatchesView;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMatchesPresenterImpl implements AllMatchesPresenter{
    private final AllMatchesView matchesView;
    private final RecyclerView recyclerView;
    private final MainActivity activity;
    private List<OpenDotaMatchesModel> matchesData;

    public AllMatchesPresenterImpl(MainActivity mainActivity, AllMatchesView allMatchesView, RecyclerView recyclerView) {
        this.matchesView = allMatchesView;
        this.recyclerView = recyclerView;
        this.activity = mainActivity;
    }

    @Override
    public void getData() {
        matchesData = MatchesApplication.getMatchesDataBase().getMatchesDao().getAllMatches();
        MatchesApplication.getApi().getData().enqueue(new Callback<List<OpenDotaMatchesModel>>() {
            @Override
            public void onResponse(Call<List<OpenDotaMatchesModel>> call, Response<List<OpenDotaMatchesModel>> response) {
                matchesView.hideTextConnection();
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
                    matchesView.showTextConnection();
                    matchesView.getPresenter().startConnectionReceive();
                }
            }
        });
    }

    @Override
    public void setData(List<OpenDotaMatchesModel> matchesData) {
        final List<OpenDotaMatchesModel> openDotaMatchesModels = matchesData;
        new ThreadPoolExecutor(4, 8, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()).execute(new Runnable() {
            @Override
            public void run() {
                getHeroNames(openDotaMatchesModels);
            }
        });
    }

    @Override
    public void getHeroNames(final List<OpenDotaMatchesModel> openDotaMatchesModels) {
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

    @Override
    public void setHeroNames(List<OpenDotaMatchesModel> openDotaMatchesModels, List<HeroNamesModel> body) {
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        MatchesListAdapter matchesListAdapter = new MatchesListAdapter(activity, body);
        recyclerView.setAdapter(matchesListAdapter);
    }
}
