package com.shine.overlap.ui.main;

import android.content.Context;
import android.content.Intent;

import com.shine.overlap.data.model.Photo;
import com.shine.overlap.ui.base.BasePresenter;
import com.shine.overlap.ui.base.BaseView;
import com.shine.overlap.ui.recycleritem.photoModel;

import java.util.List;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

public interface MainActivityContract {

    interface View extends BaseView {
        void showPhotos(List<photoModel> photoList);
    }

    interface Presenter extends BasePresenter {
        /**
         * 사진 불러오기
         */
        void loadPhotos();

        /**
         * 사진 추가
         */
        void addPhoto(Photo photo);

        void result(Context context, int requestCode, Intent data);

        void onListItemClicked(photoModel model, boolean willChecked);
    }
}
