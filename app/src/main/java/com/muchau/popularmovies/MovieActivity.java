package com.muchau.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.muchau.popularmovies.utilities.DateUtils;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    private static final String PICTURE_URL = "http://image.tmdb.org/t/p/";

    private static final String PICTURE_ORIGINAL = "original";

    private static final String PICTURE_W185 = "w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        Toast.makeText(this, "Oi:"+ movie.getTitle(), Toast.LENGTH_LONG).show();

        ImageView background = findViewById(R.id.image_view_backdrop_path);

        ImageView poster = findViewById(R.id.image_view_poster_path);

        String pictureUrl = PICTURE_URL + PICTURE_ORIGINAL + movie.getBackdropPath();

        String posterUrl = PICTURE_URL + PICTURE_W185 + movie.getPosterPath();

        Picasso.with(this)
                .load(pictureUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(background);

        Picasso.with(this)
                .load(posterUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(poster);

        TextView movieTitle = findViewById(R.id.title_text_view);
        movieTitle.setText(movie.getOriginalTitle());

        TextView releaseDate = findViewById(R.id.release_date_text_view);
        releaseDate.setText(DateUtils.formateDateFromstring("yyyy-MM-dd","MM/dd/yyyy", movie.getReleaseDate()));

        RatingBar voteAverageBar = findViewById(R.id.vote_average_rating_bar);
        voteAverageBar.setMax(10);
        voteAverageBar.setMin(0);
        voteAverageBar.setRating(movie.getVoteAverage()/2);

        TextView voteAverage = findViewById(R.id.vote_average_text_view);
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));

        TextView description = findViewById(R.id.description_text_view);
        description.setText(movie.getOverview());

    }

}