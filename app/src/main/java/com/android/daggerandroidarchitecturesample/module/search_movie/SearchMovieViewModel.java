package com.android.daggerandroidarchitecturesample.module.search_movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.android.daggerandroidarchitecturesample.util.Resource;
import javax.inject.Inject;

public class SearchMovieViewModel extends ViewModel{

    private  SearchMovieRepository searchMovieRepository;
    private LiveData<Resource<MovieResponse>> data;

    @Inject
    public SearchMovieViewModel(SearchMovieRepository searchMovieRepository) {
        this.searchMovieRepository = searchMovieRepository;
    }

    public LiveData<Resource<MovieResponse>> getMovie(String movieTitle) {
        this.data = searchMovieRepository.getMovie(movieTitle);
        return data;
    }

}

