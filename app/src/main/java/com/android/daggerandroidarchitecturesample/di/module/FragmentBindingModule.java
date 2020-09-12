package com.android.daggerandroidarchitecturesample.di.module;

import com.android.daggerandroidarchitecturesample.module.search_movie.SearchMovieFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract SearchMovieFragment provideSearchMovieFragment();

}