package com.android.daggerandroidarchitecturesample.di.module;

import com.android.daggerandroidarchitecturesample.module.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

}