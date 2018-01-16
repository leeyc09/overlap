package com.shine.overlap.di;

/**
 * Created by youngchanlee on 2018. 1. 10..
 */

import com.shine.overlap.ui.main.MainActivity;
import com.shine.overlap.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 *
 * Dagger.Android가 ActivityBindingModule 모듈이있는 부모 구성 요소가 있는 하위 구성 요소를 만들고,
 * 우리의 경우 AppComponent가됩니다. 이 설정에 대한 아름다운 부분은 AppComponent에 이러한 모든 하위 구성 요소가 있음을 알릴 필요가 없다는 것입니다
 * 이 하위 구성 요소에 AppComponent가 있음을 알릴 필요도 없습니다.
 * Dagger.Android는 SubComponent가 지정된 모듈을 포함하고 scope 어노테이션 @ActivityScoped를 인식해야한다고 말합니다.
 * Dagger.Android 주석 프로세서가 실행되면 우리에게 4 개의 하위 구성 요소가 생성됩니다.
 *
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules =
            {MainActivityModule.class})
    abstract MainActivity mainActivity();
}
