package com.example.legion.myprofilestats.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.utils.Utils;


/**
 * A simple {@link BaseFragment} subclass.
 */
public class FragmentMoreStats extends BaseFragment {

    public FragmentMoreStats() {
        // Required empty public constructor
    }

    public static FragmentMoreStats newInstance() {
        return new FragmentMoreStats();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initView(LayoutInflater inflater, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_fragment_more_stats, null);
        TextView lastHits = findViewById(R.id.values_last_hits);
        TextView valueXpm = findViewById(R.id.values_xpm);
        TextView valueGpm = findViewById(R.id.values_gpm);
        TextView heroDamage = findViewById(R.id.values_hero_dmg);
        TextView heroHealing = findViewById(R.id.values_hero_healing);
        TextView towerDamage = findViewById(R.id.values_tower_dmg);
        ImageView heroIcon = findViewById(R.id.hero_icon);
        TextView heroName = findViewById(R.id.hero_name);
        TextView isGameWin = findViewById(R.id.is_game_win);
        TextView matchDuration = findViewById(R.id.value_duration);
        TextView valueKda = findViewById(R.id.values_kda);
        TextView matchDate = findViewById(R.id.values_match_date);
        TextView gameMode = findViewById(R.id.values_game_mode);

        lastHits.setText(model.getLastHits());
        valueGpm.setText(model.getXpm());
        valueXpm.setText(model.getXpm());
        heroDamage.setText(model.getHeroDamage());
        heroHealing.setText(model.getHeroHealing());
        towerDamage.setText(model.getTowerDamage());
        Utils.getInstance().setHeroIcon(heroIcon, model);
        heroName.setText(model.getHeroName());
        Utils.getInstance().isWin(model, isGameWin);
        matchDuration.setText(Utils.getInstance().getMatchDuration(model));
        valueKda.setText(Utils.getInstance().getValueKDA(model));
        matchDate.setText(Utils.getInstance().getMatchDate(Long.parseLong(model.getMatchTime())));
        gameMode.setText(Utils.getInstance().getGameMode(model));
    }

    public void setModel(OpenDotaMatchesModel matchModel) {
        this.model = matchModel;
    }

}
