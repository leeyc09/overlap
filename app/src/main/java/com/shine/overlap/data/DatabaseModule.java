package com.shine.overlap.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.shine.overlap.data.database.PhotoDao;
import com.shine.overlap.data.database.PhotosDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

@Module
public class DatabaseModule {
    private static final String DATABASE = "overlap";

    /**
     * @Provides
     * 공용 인터페이스 제공 주석을 확장합니다. 주석모듈의 메서드에 주석을 추가하여 공급자 메서드 바인딩을 만듭니다.
     * 메서드의 반환 값 형식은 반환 값에 바인딩됩니다. 구성 요소 구현은 종속성을 매개 변수로 메소드에 전달합니다.

     * Null 가능성
     * 대거는 기본적으로 null을 주입하는 것을 금지합니다. null를 돌려주는 @Provides 메소드를 호출하는 컴퍼넌트의 구현은,
     * 그 후에 즉시 NullPointerException를 Throw합니다. @Provides 메소드는 javax.annotation.Nullable 또는
     * android.support.annotation.Nullable과 같은 @Nullable 주석을 사용하여 메소드에 주석을 추가하여 null을
     * 허용하도록 선택할 수 있습니다.
     * @Provides 메서드가 @Nullable로 표시된 경우 Dagger는 @Nullable로 표시된 사이트에만 삽입 할 수 있습니다.
     * @Nullable 조항과 Nullable이 아닌 삽입 사이트를 결합하려고하는 구성 요소는 컴파일되지 않습니다.
     *
     * @Singleton 싱글턴을 사용할 꺼야
     */
    @Provides
    @Singleton
    PhotosDatabase providePhotosDatabase(Context context) {
        return Room.databaseBuilder(context, PhotosDatabase.class, DATABASE).build();
    }


    @Provides
    @Singleton
    PhotoDao providePhotoDao(PhotosDatabase photosDatabase){
        return photosDatabase.photoDao();
    }

}


