package com.example.legion.myprofilestats.views;

import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

public interface MainActivityView {
    void showListMatchesFragment();

    void showMoreInfoFragment(OpenDotaMatchesModel matchModel);

}
