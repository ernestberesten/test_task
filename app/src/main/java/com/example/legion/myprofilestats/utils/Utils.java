package com.example.legion.myprofilestats.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.legion.myprofilestats.MatchesApplication;
import com.example.legion.myprofilestats.R;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ernest on 07.03.2018.
 */

public class Utils {
    private static final Utils ourInstance = new Utils();

    private static final Map<String, String> gameModes = new HashMap<>();
    private static final String DURATION_FORMAT = "%02d:%02d:%02d";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    static {
        gameModes.put("0", "Unknown");
        gameModes.put("1", "All Pick");
        gameModes.put("2", "Captains Mode");
        gameModes.put("3", "Random Draft");
        gameModes.put("4", "Single Draft");
        gameModes.put("5", "All Random");
        gameModes.put("15", "Custom");

    }

    private final static int RADIANT_LAST_SLOT = 128;

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public void isWin(OpenDotaMatchesModel matchModel, TextView isGameWin) {
        String value;
        boolean isWin;
        if (matchModel.getPlayerSlot() < RADIANT_LAST_SLOT) {
            isWin = matchModel.isRadiantWin();
        } else {
            isWin = !matchModel.isRadiantWin();
        }
        if (isWin) {
            value = isGameWin.getResources().getString(R.string.text_win);
            isGameWin.setTextColor(Color.GREEN);
        } else {
            value = isGameWin.getResources().getString(R.string.text_lose);
            isGameWin.setTextColor(Color.RED);
        }
        isGameWin.setText(value);
    }

    public String getMatchDuration(OpenDotaMatchesModel matchModel) {
        long millis = Long.parseLong(matchModel.getDuration());
        return String.format(Locale.getDefault(), DURATION_FORMAT,
                TimeUnit.SECONDS.toHours(millis),
                TimeUnit.SECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.SECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

    public String getValueKDA(OpenDotaMatchesModel matchModel) {
        return matchModel.getKills() + "/" + matchModel.getDeaths() + "/" + matchModel.getAssists();
    }

    public String getMatchDate(long mills) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return sdf.format(new Date(mills * 1000));
    }

    public void setHeroIcon(ImageView heroIcon, OpenDotaMatchesModel matchModel) {
        int id = heroIcon.getContext().getResources()
                .getIdentifier("id" + matchModel.getHeroId(), "drawable", heroIcon.getContext().getPackageName());
        heroIcon.setImageDrawable(heroIcon.getResources().getDrawable(id));
    }

    public String getGameMode(OpenDotaMatchesModel matchesModel) {
        String mode = "";
        String json = "";
        try {
            InputStream inputStream = MatchesApplication.getApp().getAssets().open("game_modes.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            mode = jsonObject.getJSONObject(matchesModel.getGameMode()).getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mode;
    }
}
