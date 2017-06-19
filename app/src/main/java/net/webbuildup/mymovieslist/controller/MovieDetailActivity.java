package net.webbuildup.mymovieslist.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.webbuildup.mymovieslist.data.MovieAPI;
import net.webbuildup.mymovieslist.data.NowPlaying;
import net.webbuildup.mymovieslist.data.RetrofitUtil;
import net.webbuildup.mymovieslist.models.Movie;
import net.webbuildup.mymovieslist.mymovieslist.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieDetailActivity extends AppCompatActivity {
    long longMovieID;
    @BindView(R.id.tvTitle)
    TextView title;

    @BindView(R.id.rbStars)
    RatingBar voteAverage;

    @BindView(R.id.ivCover)
    ImageView cover;

    @BindView(R.id.tvOverview)
    TextView overview;

    @BindView(R.id.tvReleaseDate)
    TextView releaseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        longMovieID = intent.getExtras().getLong("movie_id");
        Retrofit retrofit = RetrofitUtil.create();
        MovieAPI movieAPI = retrofit.create(MovieAPI.class);
        movieAPI.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                Movie movie = response.body().getMovieID(longMovieID);
                populateScreen(movie);
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void populateScreen(Movie movie) {
        this.voteAverage.setRating(movie.getVoteAverage()/2);
        this.title.setText(movie.getTitle());
        this.overview.setText(movie.getOverview());
        this.releaseDate.setText(movie.getReleaseDate());
        Context context = getApplicationContext();
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        String imagePath = "";
        if (display.getRotation() == Surface.ROTATION_90 || display.getRotation() == Surface.ROTATION_180) {
            imagePath = movie.getBackdropPath();
        } else {
            imagePath = movie.getPosterPath();
        }
        Picasso.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.error_img)
                .into(cover);
    }
}
