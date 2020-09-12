package com.android.daggerandroidarchitecturesample.api;

import com.android.daggerandroidarchitecturesample.module.search_movie.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {

    @Headers({
            "Content-Type: application/json",
    })
    @GET("/")
    Call<MovieResponse> getMovie(@Query("apikey") String apiKey,
                                 @Query("t") String movieSearchTitle,
                                 @Query("r") String responseFormat,
                                 @Query("type") String type);


}
