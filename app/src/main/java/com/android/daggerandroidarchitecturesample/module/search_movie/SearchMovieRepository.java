package com.android.daggerandroidarchitecturesample.module.search_movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.daggerandroidarchitecturesample.api.APIService;
import com.android.daggerandroidarchitecturesample.util.Constant;
import com.android.daggerandroidarchitecturesample.util.Resource;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieRepository {

    private final APIService apiService;

    @Inject
    public SearchMovieRepository(APIService apiService) {
        this.apiService = apiService;

    }

    public LiveData<Resource<MovieResponse>> getMovie(String movieTitle) {

        final MutableLiveData<Resource<MovieResponse>> data = new MutableLiveData<>();

        Call<MovieResponse> call = apiService.getMovie(Constant.API_KEY,
                movieTitle, Constant.RESPONSE_FORMAT, Constant.TYPE );
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(Resource.success(response.body()));

                }else{
                    data.setValue(Resource.error(Constant.NETWORK_ERROR_MESSAGE, null));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });

        return data;

    }

}