package com.shine.overlap.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shine.overlap.data.model.Photo;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Database(entities = {Photo.class}, version = 1)
public abstract class PhotosDatabase extends RoomDatabase {

    public abstract PhotoDao photoDao();

}
