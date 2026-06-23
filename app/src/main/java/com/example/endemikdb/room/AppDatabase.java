package com.example.endemikdb.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Favorit.class},
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoritDao favoritDao();
}