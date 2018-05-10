package com.muchau.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.muchau.popularmovies.adapter.MovieAdapter;
import com.muchau.popularmovies.sync.MovieSyncTask;
import com.muchau.popularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static SharedPreferences sharedpreferences;

    public static final String myPreference = "myPreferences";

    public static final String sortBy = "sortBy";

    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MovieAdapter(this, data);
        recyclerView.setAdapter(adapter);

        new MovieTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        SharedPreferences.Editor editor = sharedpreferences.edit();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_most_popular) {
            editor.putString(sortBy, NetworkUtils.POPULAR_SORT);
            editor.apply();
            return true;
        } else if (id == R.id.action_highest_rated) {
            editor.putString(sortBy, NetworkUtils.TOP_RATED_SORT);
            editor.apply();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class MovieTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Context context = getApplicationContext();
            MovieSyncTask.syncMovie(context);
            return null;
        }
    }
}
