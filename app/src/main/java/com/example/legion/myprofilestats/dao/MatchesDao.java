package com.example.legion.myprofilestats.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

import java.util.List;

/**
 * Created by Ernest on 06.03.2018.
 */

@Dao
public interface MatchesDao {
    @Insert
    void insertAll(List<OpenDotaMatchesModel> openDotaMatchesModels);

    @Update
    void updateAll(List<OpenDotaMatchesModel> openDotaMatchesModels);

    @Delete
    void delete(OpenDotaMatchesModel openDotaMatchesModel);

    @Query("SELECT * FROM OpenDotaMatchesModel")
    List<OpenDotaMatchesModel> getAllMatches();
}
