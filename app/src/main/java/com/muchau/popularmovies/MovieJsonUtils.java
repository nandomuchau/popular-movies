package com.muchau.popularmovies;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Luis F. Muchau on 5/9/2018.
 */
public class MovieJsonUtils {

    private static final String RESULTS = "results";
    private static final String VOTE_COUNT = "vote_count";
    private static final String ID = "id";
    private static final String VIDEO = "video";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String TITLE = "title";
    private static final String POPULARITY = "popularity";
    private static final String POSTER_PATH = "poster_path";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String GENRE_IDS = "genre_ids";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ADULT = "adult";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";

    public static ArrayList<Movie> getMovieValuesFromJson(String mainJsonStr)
            throws JSONException {

        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Movie movie = null;

        JSONObject mainJson = new JSONObject(mainJsonStr);
        JSONArray jsonMoviesArray = mainJson.getJSONArray(RESULTS);

        for (int i = 0; i < jsonMoviesArray.length(); i++) {
            movie = new Movie();

            JSONObject movieObj = jsonMoviesArray.getJSONObject(i);

            movie.setVoteCount(movieObj.getInt(VOTE_COUNT));
            movie.setId(movieObj.getInt(ID));
            movie.setVideo(movieObj.getBoolean(VIDEO));
            movie.setVoteAverage(movieObj.getInt(VOTE_AVERAGE));
            movie.setTitle(movieObj.getString(TITLE));
            movie.setPopularity(movieObj.getLong(POPULARITY));
            movie.setPosterPath(movieObj.getString(POSTER_PATH));
            movie.setOriginalLanguage(movieObj.getString(ORIGINAL_LANGUAGE));
            movie.setOriginalTitle(movieObj.getString(ORIGINAL_TITLE));
            //movie.setGenreIds(movieObj.getJSONArray(GENRE_IDS));
            movie.setBackdropPath(movieObj.getString(BACKDROP_PATH));
            movie.setAdult(movieObj.getBoolean(ADULT));
            movie.setOverview(movieObj.getString(OVERVIEW));
            movie.setReleaseDate(movieObj.getString(RELEASE_DATE));

            movieList.add(movie);
        }

        return movieList;
    }
}
