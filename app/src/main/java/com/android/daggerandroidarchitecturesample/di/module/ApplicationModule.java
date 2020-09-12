package com.android.daggerandroidarchitecturesample.di.module;

import com.android.daggerandroidarchitecturesample.api.APIService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    private static final String BASE_URL = "http://www.omdbapi.com";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

}