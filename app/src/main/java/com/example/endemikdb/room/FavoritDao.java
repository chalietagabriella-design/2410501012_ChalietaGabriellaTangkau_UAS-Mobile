package com.example.endemikdb.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritDao {

    @Insert
    void insert(Favorit favorit);

    @Delete
    void delete(Favorit favorit);

    @Query("SELECT * FROM favorit")
    List<Favorit> getAllFavorit();
}