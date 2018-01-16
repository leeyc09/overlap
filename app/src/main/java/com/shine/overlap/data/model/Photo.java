package com.shine.overlap.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Entity(tableName = "photos")
public class Photo {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "imageUri")
    private String mImageUri;

    public Photo(@NonNull String mImageUri) {
        this.mImageUri = mImageUri;
    }

    @NonNull
    public String getImageUri() {
        return mImageUri;
    }
}
