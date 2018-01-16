package com.shine.overlap;



import com.facebook.stetho.Stetho;
import com.shine.overlap.data.repository.PhotoRepository;
import com.shine.overlap.di.DaggerAppComponent;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

public class AndroidApplication extends DaggerApplication {

//    PhotoRepository photoRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    /**
     * Our Espresso tests need to be able to get an instance of the {@link PhotoRepository}
     * so that we can delete all tasks before running each test
     */
//    @VisibleForTesting
//    public PhotoRepository getPhotoRepository() {
//        return photoRepository;
//    }
}