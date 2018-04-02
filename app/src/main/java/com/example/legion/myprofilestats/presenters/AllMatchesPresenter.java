package com.example.legion.myprofilestats.presenters;

import com.example.legion.myprofilestats.models.HeroNamesModel;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

import java.util.List;

public interface AllMatchesPresenter {
    void getData();
    void setData(List<OpenDotaMatchesModel> matchesData);
    void getHeroNames(List<OpenDotaMatchesModel> openDotaMatchesModels);
    void setHeroNames(List<OpenDotaMatchesModel> openDotaMatchesModels, List<HeroNamesModel> body);
}
