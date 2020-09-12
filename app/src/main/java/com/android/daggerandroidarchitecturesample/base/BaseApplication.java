package com.android.daggerandroidarchitecturesample.base;

import com.android.daggerandroidarchitecturesample.di.component.ApplicationComponent;
import com.android.daggerandroidarchitecturesample.di.component.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}