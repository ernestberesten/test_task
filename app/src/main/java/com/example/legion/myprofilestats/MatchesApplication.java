package com.example.legion.myprofilestats;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.legion.myprofilestats.db.MatchesDataBase;
import com.example.legion.myprofilestats.utils.OpenDotaApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ernest on 06.03.2018.
 */

public class MatchesApplication extends Application {

    private static MatchesApplication instance;
    private static MatchesDataBase matchesDataBase;
    private static OpenDotaApi openDotaApi;

    public static MatchesApplication getApp() {
        return instance;
    }

    public static OpenDotaApi getApi() {
        return openDotaApi;
    }

    public static MatchesDataBase getMatchesDataBase() {
        return matchesDataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        matchesDataBase = Room.databaseBuilder(getApplicationContext(),
                MatchesDataBase.class, "populus-database").allowMainThreadQueries().build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.opendota.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        openDotaApi = retrofit.create(OpenDotaApi.class);
    }


}
