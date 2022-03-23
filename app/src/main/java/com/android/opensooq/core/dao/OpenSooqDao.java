package com.android.opensooq.core.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.opensooq.core.models.request.FavouriteModel;

import java.util.List;

@Dao
public interface OpenSooqDao {

    @Insert
    void insertFavouriteCity(FavouriteModel favouriteModel);

    @Query("SELECT * FROM FavouriteModel")
    List<FavouriteModel> getFavouriteCities();
}