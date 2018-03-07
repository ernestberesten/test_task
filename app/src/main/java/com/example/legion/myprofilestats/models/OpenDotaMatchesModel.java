package com.example.legion.myprofilestats.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ernest on 06.03.2018.
 */

@Entity
public class OpenDotaMatchesModel {
    @SerializedName("match_id")
    @Expose
    @PrimaryKey
    private long matchId;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("game_mode")
    @Expose
    private String gameMode;

    @SerializedName("hero_id")
    @Expose
    private int heroId;

    @SerializedName("start_time")
    @Expose
    private String matchTime;

    @SerializedName("kills")
    @Expose
    private String kills;

    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("assists")
    @Expose
    private String assists;

    @SerializedName("xp_per_min")
    @Expose
    private String xpm;

    @SerializedName("gold_per_min")
    @Expose
    private String gpm;

    @SerializedName("hero_damage")
    @Expose
    private String heroDamage;

    @SerializedName("hero_healing")
    @Expose
    private String heroHealing;

    @SerializedName("tower_damage")
    @Expose
    private String towerDamage;

    @SerializedName("last_hits")
    @Expose
    private String lastHits;

    @SerializedName("radiant_win")
    @Expose
    private boolean isRadiantWin;

    @SerializedName("player_slot")
    @Expose
    private int playerSlot;

    private String heroName;

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public String getXpm() {
        return xpm;
    }

    public void setXpm(String xpm) {
        this.xpm = xpm;
    }

    public String getGpm() {
        return gpm;
    }

    public void setGpm(String gpm) {
        this.gpm = gpm;
    }

    public String getHeroDamage() {
        return heroDamage;
    }

    public void setHeroDamage(String heroDamage) {
        this.heroDamage = heroDamage;
    }

    public String getHeroHealing() {
        return heroHealing;
    }

    public void setHeroHealing(String heroHealing) {
        this.heroHealing = heroHealing;
    }

    public String getTowerDamage() {
        return towerDamage;
    }

    public void setTowerDamage(String towerDamage) {
        this.towerDamage = towerDamage;
    }

    public String getLastHits() {
        return lastHits;
    }

    public void setLastHits(String lastHits) {
        this.lastHits = lastHits;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public boolean isRadiantWin() {
        return isRadiantWin;
    }

    public void setRadiantWin(boolean radiantWin) {
        isRadiantWin = radiantWin;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public void setPlayerSlot(int playerSlot) {
        this.playerSlot = playerSlot;
    }
}
