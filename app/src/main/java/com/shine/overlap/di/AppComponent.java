package com.shine.overlap.di;

import android.app.Application;

import com.shine.overlap.AndroidApplication;
import com.shine.overlap.data.DatabaseModule;
import com.shine.overlap.data.PhotoRepositoryModule;
import com.shine.overlap.data.repository.PhotoRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DatabaseModule.class,
        PhotoRepositoryModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<AndroidApplication> {

//    PhotoRepository getPhotoRepository();

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    // TODO: 2018. 1. 10.  기존 방식을 사용안해도 되나보다...?
    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

}
