package com.shine.overlap.data;

import com.shine.overlap.data.repository.Local;
import com.shine.overlap.data.repository.PhotoDataSource;
import com.shine.overlap.data.repository.local.PhotoLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Module
public class PhotoRepositoryModule {

    /**
     * 로컬 저장 데이터 데이터소스
     * @param photoLocalDataSource
     * @return
     */
    @Provides
    @Local
    @Singleton
    public PhotoDataSource provideLocalDataSource(PhotoLocalDataSource photoLocalDataSource){
        return photoLocalDataSource;
    }
}
