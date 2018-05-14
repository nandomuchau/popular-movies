package com.muchau.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        Toast.makeText(this, "Oi:"+ movie.getTitle(), Toast.LENGTH_LONG).show();

    }

}
