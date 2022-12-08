package com.example.ywca_weatherappf22;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {City.class})
public abstract class CitiesDatabase extends RoomDatabase {
    public abstract CityDao getDao();
}



