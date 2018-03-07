package com.example.legion.myprofilestats.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.legion.myprofilestats.dao.MatchesDao;
import com.example.legion.myprofilestats.models.OpenDotaMatchesModel;

/**
 * Created by Ernest on 06.03.2018.
 */

@Database(entities = {OpenDotaMatchesModel.class}, version = 1)
public abstract class MatchesDataBase extends RoomDatabase{
    public abstract MatchesDao getMatchesDao();
}
