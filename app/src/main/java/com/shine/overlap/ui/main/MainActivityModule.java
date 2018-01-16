package com.shine.overlap.ui.main;

import com.shine.overlap.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Module
public abstract class MainActivityModule {

    @ActivityScoped
    @Binds
    abstract MainActivityContract.View bindView(MainActivity activity);

    @ActivityScoped
    @Binds
    abstract MainActivityContract.Presenter bindPresenter(MainActivityPresenter presenter);


}
