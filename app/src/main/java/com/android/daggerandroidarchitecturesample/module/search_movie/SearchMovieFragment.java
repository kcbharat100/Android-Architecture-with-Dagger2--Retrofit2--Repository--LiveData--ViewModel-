package com.android.daggerandroidarchitecturesample.module.search_movie;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.android.daggerandroidarchitecturesample.R;
import com.android.daggerandroidarchitecturesample.base.BaseFragment;
import com.android.daggerandroidarchitecturesample.util.Constant;
import com.android.daggerandroidarchitecturesample.util.GlideApp;
import com.android.daggerandroidarchitecturesample.util.ViewModelFactory;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;
import butterknife.BindView;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class SearchMovieFragment extends BaseFragment{

    @BindView(R.id.etSearchMovie)
    EditText etSearchTitle;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvReleasedDate)
    TextView tvReleasedDate;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.tvGenre)
    TextView tvGenre;
    @BindView(R.id.tvActors)
    TextView tvActors;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    SearchMovieViewModel searchMovieViewModel;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_search_movie;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        searchMovieViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory ).get(SearchMovieViewModel.class);

        etSearchTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                    apiCall(etSearchTitle.getText().toString().trim());

                }
                return false;
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apiCall(etSearchTitle.getText().toString().trim());

            }
        });


     }

    public void apiCall(String searchTitle) {

        //hide soft key
        InputMethodManager inputMethodManager = (InputMethodManager) getBaseActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getBaseActivity().getCurrentFocus().getWindowToken(), 0);

        progressBar.setVisibility(View.VISIBLE);

        searchMovieViewModel.getMovie(searchTitle).observe(getBaseActivity(), response ->{

            switch (response.status) {

                case SUCCESS:
                    if(response.data.getResponse().equals("True")){
//                        Log.d("Data Response:", response.data.getTitle());
                        GlideApp.with(this)
                                .load(response.data.getPoster())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        progressBar.setVisibility(View.GONE);
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        progressBar.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .into(imageView);

                        tvTitle.setText(response.data.getTitle());
                        tvReleasedDate.setText(response.data.getReleased());
                        tvRating.setText(response.data.getRatings().get(0).getValue());
                        tvGenre.setText(response.data.getGenre());
                        tvActors.setText(response.data.getActors());

                    } else {
//                        Log.d("Data Response:", "Movie not found!");
                        Toast.makeText(getContext(),"Movie not found!",Toast.LENGTH_SHORT).show();
                    }

                    break;

                case LOADING:

                    break;

                case ERROR:
//                    Log.d("Error Response:",response.message);
                    Toast.makeText(getContext(), Constant.NETWORK_ERROR_MESSAGE,Toast.LENGTH_SHORT).show();
                    break;

            }

        });

    }
}

