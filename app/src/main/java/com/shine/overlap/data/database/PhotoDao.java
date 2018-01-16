package com.shine.overlap.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.shine.overlap.data.model.Photo;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Dao
public interface PhotoDao {

    @Query("SELECT * from photos")
    Flowable<List<Photo>> getPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(Photo photo);
}
