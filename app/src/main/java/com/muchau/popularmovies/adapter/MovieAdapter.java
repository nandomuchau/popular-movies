package com.muchau.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muchau.popularmovies.MainActivity;
import com.muchau.popularmovies.Movie;
import com.muchau.popularmovies.MovieActivity;
import com.muchau.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Luis F. Muchau on 5/9/2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> mData;

    private LayoutInflater mInflater;

    private Context mContext;

    private static final String PICTURE_URL = "http://image.tmdb.org/t/p/";

    private static final String PICTURE_W185 = "w185";

    // data is passed into the constructor
    public MovieAdapter(Context context, ArrayList<Movie> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.content_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mData.get(position);
        String pictureUrl = PICTURE_URL + PICTURE_W185 + movie.getPosterPath();
        Picasso.with(mContext)
            .load(pictureUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .into(holder.movieImage);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView movieImage;

        ViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.image_view_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Movie movie = getItem(getAdapterPosition());
            if(mContext instanceof MainActivity){
                ((MainActivity)mContext).loadMovieDetailsActivity(movie);
            }
        }
    }

    public void updateMovies(ArrayList<Movie> movies) {
        this.mData = movies;
    }

    Movie getItem(int id) {
        return mData.get(id);
    }
}