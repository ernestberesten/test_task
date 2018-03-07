package com.example.legion.myprofilestats.utils;

import com.example.legion.myprofilestats.models.HeroNamesModel;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ernest on 06.03.2018.
 */

public interface OpenDotaApi {
    @GET("players/113022167/recentMatches")
    Call<List<OpenDotaMatchesModel>> getData();

    @GET("heroes")
    Call<List<HeroNamesModel>> getHeroName();
}
