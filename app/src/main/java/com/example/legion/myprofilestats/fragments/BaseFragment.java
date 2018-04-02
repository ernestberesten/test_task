package com.example.legion.myprofilestats.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.legion.myprofilestats.activities.MainActivity;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

/**
 * Created by Ernest on 06.03.2018.
 */

public abstract class BaseFragment extends Fragment {
    protected MainActivity mainActivity;
    protected View currentView; //View of fragment
    protected OpenDotaMatchesModel model;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, savedInstanceState);
        return currentView;
    }

    protected <T extends View> T findViewById(int id) {
        return currentView == null ? null : (T) currentView.findViewById(id);
    }

    protected abstract void initView(LayoutInflater inflater, Bundle savedInstanceState);


}
