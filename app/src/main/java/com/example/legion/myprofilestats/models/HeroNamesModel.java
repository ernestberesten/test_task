package com.example.legion.myprofilestats.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ernest on 06.03.2018.
 */

public class HeroNamesModel {
    @SerializedName("localized_name")
    @Expose
    private String heroName;

    @SerializedName("id")
    @Expose
    private int heroId;


    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }
}
