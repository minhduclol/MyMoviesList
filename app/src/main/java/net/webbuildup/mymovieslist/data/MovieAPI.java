package net.webbuildup.mymovieslist.data;

import net.webbuildup.mymovieslist.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Duc Nguyen on 6/16/2017.
 */

public interface MovieAPI {
    @GET("now_playing")
    Call<NowPlaying> nowPlaying();
}
