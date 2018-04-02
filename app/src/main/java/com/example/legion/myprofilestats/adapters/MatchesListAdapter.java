package com.example.legion.myprofilestats.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.activities.MainActivity;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;
import com.example.legion.myprofilestats.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ernest on 06.03.2018.
 */

public class MatchesListAdapter extends RecyclerView.Adapter<MatchesListAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<OpenDotaMatchesModel> matchModelList;
    private final MainActivity mainActivity;


    public MatchesListAdapter(MainActivity mainActivity, List<OpenDotaMatchesModel> matchModelList) {
        this.matchModelList = matchModelList;
        this.mainActivity = mainActivity;
        this.layoutInflater = LayoutInflater.from(mainActivity);
    }

    @Override
    public MatchesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.item_recycler_match, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OpenDotaMatchesModel matchModel = matchModelList.get(position);
        Utils.getInstance().setHeroIcon(holder.heroIcon, matchModel);
        holder.matchDuration.setText(Utils.getInstance().getMatchDuration(matchModel));
        holder.heroName.setText(matchModel.getHeroName());
        Utils.getInstance().isWin(matchModel, holder.isGameWin);
        holder.buttonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.showMoreInfoFragment(matchModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hero_icon) ImageView heroIcon;
        @BindView(R.id.hero_name) TextView heroName;
        @BindView(R.id.is_game_win) TextView isGameWin;
        @BindView(R.id.value_duration) TextView matchDuration;
        @BindView(R.id.button_more_info) Button buttonMoreInfo;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
