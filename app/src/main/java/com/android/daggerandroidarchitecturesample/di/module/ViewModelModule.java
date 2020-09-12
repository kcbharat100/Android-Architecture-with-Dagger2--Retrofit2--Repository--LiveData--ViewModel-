package com.android.daggerandroidarchitecturesample.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.daggerandroidarchitecturesample.di.util.ViewModelKey;
import com.android.daggerandroidarchitecturesample.module.search_movie.SearchMovieViewModel;
import com.android.daggerandroidarchitecturesample.util.ViewModelFactory;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchMovieViewModel.class)
    abstract ViewModel bindSearchMovieViewModel(SearchMovieViewModel searchMovieViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}