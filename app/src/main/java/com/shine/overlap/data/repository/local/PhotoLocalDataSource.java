package com.shine.overlap.data.repository.local;

import com.shine.overlap.data.database.PhotoDao;
import com.shine.overlap.data.model.Photo;
import com.shine.overlap.data.repository.PhotoDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */
@Singleton
public class PhotoLocalDataSource implements PhotoDataSource {

    private final PhotoDao photoDao;

    @Inject
    public PhotoLocalDataSource(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public Flowable<List<Photo>> getPhotos() {
        return photoDao.getPhotos();
    }

    @Override
    public void insertOrUpdatePhotos(Photo photo) {
        photoDao.insertPhoto(photo);
    }
}
