package com.example.ywca_weatherappf22;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CityDao {

    @Insert
    void addNewFavCity(City c);

    @Query("select * from City")
    City[] getAllCities();

    @Delete
    void deleteACity(City dc);
}
