package com.shine.overlap.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by youngchanlee on 2018. 1. 10..
 * 이것은 대거 모듈입니다.
 * 우리는 이것을 사용하여 App 클래스의 Application 클래스를 AppComponent의 컨텍스트로 바인딩합니다.
 * Dagger Android를 사용하면 Application 인스턴스를 모든 모듈에 전달할 필요가 없습니다.
 * 우리는 우리의 응용 프로그램을 컨텍스트로 노출하기 만하면됩니다.
 * Dagger의 장점 중 하나 .Android는 응용 프로그램 및 활동이 그래프로 제공됩니다.
 * @linkAppComponent
 */
@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    //todo Binds는 뭘까? abstract 에서는 binds ?
    @Binds
    abstract Context bindContext(Application application);
}
