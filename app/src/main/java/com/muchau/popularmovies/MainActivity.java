package com.muchau.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.muchau.popularmovies.adapter.MovieAdapter;
import com.muchau.popularmovies.sync.MovieAsyncTask;
import com.muchau.popularmovies.utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * Created by Luis F. Muchau on 5/8/2018.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static SharedPreferences sharedpreferences;

    public static final String myPreference = "myPreferences";

    public static final String sortBy = "sortBy";

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        movieAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        recyclerView.setAdapter(movieAdapter);

        new MovieAsyncTask(this, movieAdapter).execute();
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
}
