package net.webbuildup.mymovieslist.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import net.webbuildup.mymovieslist.data.MovieAPI;
import net.webbuildup.mymovieslist.data.NowPlaying;
import net.webbuildup.mymovieslist.data.RetrofitUtil;
import net.webbuildup.mymovieslist.models.Movie;
import net.webbuildup.mymovieslist.mymovieslist.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lvMovies)
    ListView lvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateNowPlayingMovies();

        ButterKnife.bind(this);
    }
    private void populateNowPlayingMovies() {
        //lvMovies = (ListView) findViewById(R.id.lvMovies);
        Retrofit retrofit = RetrofitUtil.create();
        MovieAPI movieAPI = retrofit.create(MovieAPI.class);
        movieAPI.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                List<Movie> movies = response.body().getMovieList();
                lvMovies.setAdapter(new MovieAdapter(MainActivity.this,movies));
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
