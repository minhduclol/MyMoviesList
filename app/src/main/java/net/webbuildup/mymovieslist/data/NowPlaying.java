package net.webbuildup.mymovieslist.data;

import com.google.gson.annotations.SerializedName;

import net.webbuildup.mymovieslist.models.Movie;

import java.util.List;

/**
 * Created by Duc Nguyen on 6/17/2017.
 */

public class NowPlaying {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovieList() {
        return movies;
    }
    public Movie getMovieID(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return movies.get(0);
    }
}
