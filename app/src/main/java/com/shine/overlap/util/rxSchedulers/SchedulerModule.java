package com.shine.overlap.util.rxSchedulers;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.shine.overlap.util.rxSchedulers.SchedulerType.IO;
import static com.shine.overlap.util.rxSchedulers.SchedulerType.UI;

/**
 * Provides common Schedulers used by RxJava
 */
@Module
public class SchedulerModule {

    @Provides
    @RunOn(IO)
    Scheduler provideIo(){
        return Schedulers.io();
    }

    @Provides
    @RunOn(UI)
    Scheduler provideUi() {
        return AndroidSchedulers.mainThread();
    }
}
