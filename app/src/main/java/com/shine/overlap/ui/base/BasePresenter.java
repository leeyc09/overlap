package com.shine.overlap.ui.base;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */
public interface BasePresenter {
//    Binds presenter with a view when resumed. The Presenter will perform initialization here.
    void onAttach();
//         * Drops the reference to the view when destroyed
    void onDetach();
}
