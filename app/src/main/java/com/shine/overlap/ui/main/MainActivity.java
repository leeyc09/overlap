package com.shine.overlap.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder;
import com.shine.overlap.R;
import com.shine.overlap.ui.Config;
import com.shine.overlap.ui.recycleritem.photoModel;
import com.shine.overlap.util.PermissionMethods;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by youngchanlee on 2018. 1. 9..
 */

public class MainActivity extends DaggerAppCompatActivity implements MainActivityContract.View {
    public static final int MAX_SPAN_COUNT = 3;

    @Inject
    MainActivityContract.Presenter mMainActivityPresenter;

    //viewbind
    @BindView(R.id.photorecycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton addPhotoFab;

    private RendererRecyclerViewAdapter mRecyclerViewAdapter;

    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //todo 퍼미션은 다른 라이브러리로 바꾸도록 합시다.
        PermissionMethods.askOverlayPermission(this, Config.REQUEST_CODE_PERMISSION_OVERLAY);
        PermissionMethods.askPermission(this, PermissionMethods.StoragePermission, Config.REQUEST_CODE_PERMISSION_STORAGE);

        addPhotoFab.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, Config.REQUEST_CODE_ACTIVITY_PICTURE);
        });



        /**
         * set RecyclerView adapter
         */
        mLayoutManager = new GridLayoutManager(this, MAX_SPAN_COUNT);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerViewAdapter = new RendererRecyclerViewAdapter();
        mRecyclerViewAdapter.registerRenderer(new ViewBinder<>(R.layout.item_user_base, photoModel.class, this,
                (model, finder, payloads) -> {
                    finder.getRootView().setOnClickListener(view -> {
                        final boolean willChecked = finder.find(R.id.check).getVisibility() == GONE;
                        finder.find(R.id.check).setVisibility(willChecked ? VISIBLE : GONE);
                            mMainActivityPresenter.onListItemClicked(model, willChecked);
                    });

                    finder.find(R.id.check).setOnClickListener(view -> {
                        final boolean willChecked = finder.find(R.id.check).getVisibility() == GONE;
                        finder.find(R.id.check).setVisibility(willChecked ? VISIBLE : GONE);
                            mMainActivityPresenter.onListItemClicked(model, willChecked);
                    });

                }));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.REQUEST_CODE_PERMISSION_OVERLAY) {
            PermissionMethods.delayOverlayPermissionCheck(this);
        } else {
            mMainActivityPresenter.result(this, requestCode, data);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Config.REQUEST_CODE_PERMISSION_STORAGE) {
            boolean run = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                        PermissionMethods.explainPermission(this, PermissionMethods.StoragePermission, Config.REQUEST_CODE_PERMISSION_STORAGE);
                    } else {
                        Toast.makeText(this, R.string.permission_warn_storage_intent, Toast.LENGTH_SHORT).show();
                    }
                    run = false;
                    break;
                }
            }
            if (run && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Settings.canDrawOverlays(this)) {
                    //윈도우 메니져를 실행하는 코드가 삽입되어 있었음
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showPhotos(List<photoModel> photoList) {
        Log.d("TAG", "showPhotos: ");
        mRecyclerViewAdapter.setItems(photoList);
        mRecyclerViewAdapter.notifyDataSetChanged();

    }


}