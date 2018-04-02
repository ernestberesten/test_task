package com.example.legion.myprofilestats.views;

import com.example.legion.myprofilestats.presenters.MainActivityPresenter;

public interface AllMatchesView {
    void showTextConnection();

    void hideTextConnection();

    MainActivityPresenter getPresenter();
}
