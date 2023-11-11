package com.anstudio.travelblog.database;

import androidx.room.*;

import com.anstudio.travelblog.http.*;

@Database(entities = {Blog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BlogDAO blogDao();
}