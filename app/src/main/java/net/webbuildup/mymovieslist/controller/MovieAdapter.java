package net.webbuildup.mymovieslist.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import net.webbuildup.mymovieslist.models.Movie;
import net.webbuildup.mymovieslist.mymovieslist.R;

import java.util.List;

/**
 * Created by Duc Nguyen on 6/17/2017.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    private List<Movie> movies;
    public MovieAdapter(@NonNull Context context, List<Movie> movies) {
        super(context, -1);
        this.movies = movies;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from((getContext()))
                    .inflate(R.layout.activity_movie_item, parent, false); //List view doesn't allow attachToRoot to be true
            viewHolder = new ViewHolder(convertView);
            //dont allow garbage collection to kill viewHolder
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Fill in data
        Movie movie = movies.get(position);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        Context context = getContext();
        /* Glide.with(getContext())
                .load(movie.getPosterPath())
                .into(viewHolder.ivCover); */
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        String imagePath = "";
        if (display.getRotation() == Surface.ROTATION_90 || display.getRotation() == Surface.ROTATION_180) {
            imagePath = movie.getBackdropPath();
        } else {
            imagePath = movie.getPosterPath();
        }
        Picasso.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.error_img)
                .into(viewHolder.ivCover);

        return convertView;
    }

    @Override
    public int getCount() {
        return movies.size();
    }
    private class ViewHolder {
        final ImageView ivCover;
        final TextView tvTitle;
        final TextView tvOverview;
        public ViewHolder(View convertView) {
            ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        }
    }
}
