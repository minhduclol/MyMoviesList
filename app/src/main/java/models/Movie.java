package models;

/**
 * Created by Duc Nguyen on 6/15/2017.
 */

public class Movie {
    static int voteCount;
    static int id;
    static boolean video;
    static float voteAverage;
    static String title;
    static float popularity;
    static String posterPath;
    static String originalLanguage;
    static String originalTitle;
    static int[] genre_ids;
    static String backdropPath;
    static boolean adult;
    static String overview;
    static String releaseDate;

    public Movie() {
    }

    public static int getVoteCount() {
        return voteCount;
    }

    public static int getId() {
        return id;
    }

    public static boolean isVideo() {
        return video;
    }

    public static float getVoteAverage() {
        return voteAverage;
    }

    public static String getTitle() {
        return title;
    }

    public static float getPopularity() {
        return popularity;
    }

    public static String getPosterPath() {
        return posterPath;
    }

    public static String getOriginalLanguage() {
        return originalLanguage;
    }

    public static String getOriginalTitle() {
        return originalTitle;
    }

    public static int[] getGenre_ids() {
        return genre_ids;
    }

    public static String getBackdropPath() {
        return backdropPath;
    }

    public static boolean isAdult() {
        return adult;
    }

    public static String getOverview() {
        return overview;
    }

    public static String getReleaseDate() {
        return releaseDate;
    }

    public static void setVoteCount(int voteCount) {
        Movie.voteCount = voteCount;
    }

    public static void setVideo(boolean video) {
        Movie.video = video;
    }

    public static void setVoteAverage(float voteAverage) {
        Movie.voteAverage = voteAverage;
    }

    public static void setTitle(String title) {
        Movie.title = title;
    }

    public static void setPopularity(float popularity) {
        Movie.popularity = popularity;
    }

    public static void setPosterPath(String posterPath) {
        Movie.posterPath = posterPath;
    }

    public static void setOriginalLanguage(String originalLanguage) {
        Movie.originalLanguage = originalLanguage;
    }

    public static void setOriginalTitle(String originalTitle) {
        Movie.originalTitle = originalTitle;
    }

    public static void setGenre_ids(int[] genre_ids) {
        Movie.genre_ids = genre_ids;
    }

    public static void setBackdropPath(String backdropPath) {
        Movie.backdropPath = backdropPath;
    }

    public static void setAdult(boolean adult) {
        Movie.adult = adult;
    }

    public static void setOverview(String overview) {
        Movie.overview = overview;
    }

    public static void setReleaseDate(String releaseDate) {
        Movie.releaseDate = releaseDate;
    }
}
