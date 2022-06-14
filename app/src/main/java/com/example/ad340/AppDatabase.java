package com.example.ad340;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ad340.settings.Settings;
import com.example.ad340.settings.SettingsDao;

@Database(entities = {Settings.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}
