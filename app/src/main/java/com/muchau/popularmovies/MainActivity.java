package com.muchau.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);

        /*ImageView image = findViewById(R.id.imageView);

        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(image);*/

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
