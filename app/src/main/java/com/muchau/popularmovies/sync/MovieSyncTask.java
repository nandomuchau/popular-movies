package com.muchau.popularmovies.sync;

import android.content.Context;
import android.util.Log;

import com.muchau.popularmovies.Movie;
import com.muchau.popularmovies.MovieJsonUtils;
import com.muchau.popularmovies.utilities.NetworkUtils;
import java.net.URL;
import java.util.ArrayList;

public class MovieSyncTask {

    private static final String TAG = MovieSyncTask.class.getSimpleName();

    synchronized public static void syncMovie(Context context) {
        try {
            URL requestUrl = NetworkUtils.getUrl(context);

            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(requestUrl);
            Log.v(TAG, "jsonResponse: " + jsonResponse);
            /* Parse the JSON into a list of weather values */
            /*ContentValues[] weatherValues = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, jsonWeatherResponse);*/
            ArrayList<Movie> movies = MovieJsonUtils.getMovieValuesFromJson(context, jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}