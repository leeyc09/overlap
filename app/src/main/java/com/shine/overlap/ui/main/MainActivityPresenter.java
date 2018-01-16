package com.shine.overlap.ui.main;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shine.overlap.data.model.Photo;
import com.shine.overlap.data.repository.PhotoRepository;
import com.shine.overlap.di.ActivityScoped;
import com.shine.overlap.ui.Config;
import com.shine.overlap.ui.recycleritem.photoModel;
import com.shine.overlap.util.UriMethods;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.transform.Result;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * View에서 전달된 이벤트에 대한 처리를 한다
 * Created by youngchanlee on 2018. 1. 10..
 *
 * A presenter with life-cycle aware.
 */
@ActivityScoped
public class MainActivityPresenter implements MainActivityContract.Presenter, LifecycleObserver {

    String TAG = MainActivityPresenter.class.getSimpleName();

    private final PhotoRepository mPhotoRepository;

    private MainActivityContract.View mMainView;

    private CompositeDisposable disposeBag;

    private final ArrayList<photoModel> mSelectedPhotos = new ArrayList<>();


    @Inject
    public MainActivityPresenter(MainActivityContract.View view, PhotoRepository mPhotoRepository) {
        this.mMainView = view;
        this.mPhotoRepository = mPhotoRepository;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
        Log.d(TAG, "ON_RESUME: onAttach");
        loadPhotos();
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
     public void onDetach() {
        // Clean up any no-longer-use resources here
        Log.d(TAG, "onDetach: ");
        disposeBag.clear();
    }

    @Override
    public void loadPhotos() {
        Disposable disposable = mPhotoRepository.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos ->
                {
                    Log.d(TAG, "loadPhotos: photos size : "+ photos.size());
                    List<photoModel> photoModels = new ArrayList<>();
                    for(Photo photo: photos){
                        photoModels.add(new photoModel(photo.getImageUri()));
                    }

                    mMainView.showPhotos(photoModels);
                }, throwable -> throwable.printStackTrace());

        disposeBag.add(disposable);
    }

    @Override
    public void addPhoto(Photo photo) {
        Completable.fromAction(() -> mPhotoRepository.insertOrUpdatePhotos(photo))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(() ->

                            Log.e(TAG, "addPhoto: Complete"),
                throwable -> Log.e(TAG, "Unable to update username", throwable));
    }

    @Override
    public void result(Context context, int requestCode, Intent data) {
        if(requestCode == Config.REQUEST_CODE_ACTIVITY_PICTURE){
                if (data != null) {
                    if(UriMethods.getImageAbsolutePath(context, data.getData()) != null)
                        addPhoto(new Photo(UriMethods.getImageAbsolutePath(context, data.getData())));
                }
        }
    }

    @Override
    public void onListItemClicked(photoModel model, boolean isChecked) {
        if (isChecked) {
            mSelectedPhotos.add(model);
        } else {
            mSelectedPhotos.remove(model);
        }
    }


}
