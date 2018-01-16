package com.shine.overlap.data.repository;

import com.shine.overlap.data.model.Photo;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by youngchanlee on 2018. 1. 10..
 *
 * Access point for managing photo data.
 */
public interface PhotoDataSource {

    Flowable<List<Photo>> getPhotos();

    void insertOrUpdatePhotos(Photo photo);
}
