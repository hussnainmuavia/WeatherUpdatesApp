package com.android.opensooq.core.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import org.jetbrains.annotations.NotNull;
import java.util.List;

@Dao
public interface OpenSooqDao {

    /*
    @Query("SELECT * FROM Items")
    List<Items> getAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(Items items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavouriteItems(FavouriteTracks favouriteTracks);

    @Query("SELECT * FROM FavouriteTracks")
    List<FavouriteTracks> getFavouriteItems();

    @Delete
    void deleteFavouriteTrack(FavouriteTracks favouriteTracks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavouriteSong(@NotNull FavouriteSong track);

    @Query("SELECT * FROM FavouriteSong")
    List<FavouriteSong> getAllFavouriteSong();

    @Query("DELETE FROM Items")
    void deleteAllItems();
    */
}