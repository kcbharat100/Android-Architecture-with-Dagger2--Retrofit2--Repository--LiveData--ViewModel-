package com.android.daggerandroidarchitecturesample.module;

import android.os.Bundle;
import com.android.daggerandroidarchitecturesample.R;
import com.android.daggerandroidarchitecturesample.base.BaseActivity;
import com.android.daggerandroidarchitecturesample.module.search_movie.SearchMovieFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().add(R.id.fragment_container, new SearchMovieFragment()).commit();


    }
}
