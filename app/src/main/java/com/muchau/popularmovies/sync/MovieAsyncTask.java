package com.muchau.popularmovies.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.muchau.popularmovies.Movie;
import com.muchau.popularmovies.MovieJsonUtils;
import com.muchau.popularmovies.adapter.MovieAdapter;
import com.muchau.popularmovies.utilities.NetworkUtils;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Luis F. Muchau on 5/9/2018.
 */
public class MovieAsyncTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    private MovieAdapter movieAdapter;

    private ArrayList<Movie> movies;

    private static final String TAG = MovieAsyncTask.class.getSimpleName();

    public MovieAsyncTask(Context context, MovieAdapter movieAdapter){
        this.context = context;
        this.movieAdapter = movieAdapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL requestUrl = NetworkUtils.getUrl(this.context);

            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(requestUrl);
            Log.v(TAG, "jsonResponse: " + jsonResponse);
            /* Parse the JSON into a list of weather values */
            /*ContentValues[] weatherValues = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, jsonWeatherResponse);*/
            this.movies = MovieJsonUtils.getMovieValuesFromJson(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        movieAdapter.updateMovies(this.movies);
        movieAdapter.notifyDataSetChanged();
    }
}