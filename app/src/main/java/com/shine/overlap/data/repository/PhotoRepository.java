package com.shine.overlap.data.repository;

import com.shine.overlap.data.model.Photo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */
@Singleton
public class PhotoRepository implements PhotoDataSource {

    private PhotoDataSource localPhotoDataSource;

    @Inject
    public PhotoRepository(@Local PhotoDataSource localPhotoDataSource) {
        this.localPhotoDataSource = localPhotoDataSource;
    }

    @Override
    public Flowable<List<Photo>> getPhotos() {
        return localPhotoDataSource.getPhotos();
    }

    @Override
    public void insertOrUpdatePhotos(Photo photo) {
        localPhotoDataSource.insertOrUpdatePhotos(photo);
    }
}
