package com.muchau.popularmovies.utilities;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.muchau.popularmovies.MainActivity;
import com.muchau.popularmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Luis F. Muchau on 5/8/2018.
 * These utilities will be used to communicate with the movie servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL = "https://api.themoviedb.org/3";

    public static final String TOP_RATED_SORT = "/movie/top_rated";

    public static final String POPULAR_SORT = "/movie/popular";

    private static final String API_PARAM = "api_key";

    public static URL getUrl(Context context) {
        String apiKey = context.getString(R.string.api_key);
        String sortBy = POPULAR_SORT;
        if (MainActivity.sharedpreferences.contains(MainActivity.sortBy)) {
            sortBy = MainActivity.sharedpreferences.getString(MainActivity.sortBy, POPULAR_SORT);
            Log.d(TAG, "sortBy: " + sortBy);
        }

        Uri movieQueryUri = Uri.parse(BASE_URL + sortBy).buildUpon()
                .appendQueryParameter(API_PARAM, apiKey)
                .build();

        try {
            URL movieQueryUrl = new URL(movieQueryUri.toString());
            Log.v(TAG, "URL: " + movieQueryUrl);
            return movieQueryUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response, null if no response
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }
}