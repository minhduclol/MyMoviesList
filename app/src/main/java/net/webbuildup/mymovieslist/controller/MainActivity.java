package net.webbuildup.mymovieslist.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.webbuildup.mymovieslist.data.MovieAPI;
import net.webbuildup.mymovieslist.data.NowPlaying;
import net.webbuildup.mymovieslist.data.RetrofitUtil;
import net.webbuildup.mymovieslist.models.Movie;
import net.webbuildup.mymovieslist.mymovieslist.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lvMovies)
    ListView lvMovies;
    List<Movie> movies;
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
                movies = response.body().getMovieList();
                lvMovies.setAdapter(new MovieAdapter(MainActivity.this,movies));
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @OnItemClick(R.id.lvMovies)
    public void viewMovieDetail (AdapterView<?> parent, View view, int position, long id) {
        Movie viewMovie = movies.get(position);
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie_id", viewMovie.getId());
        startActivity(intent);
    }
}
