package com.mostafaabdel_fatah.movieapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.zip.Inflater;


public class MovieAdapter extends ArrayAdapter  {

    private List<Movie> moviesList;
    private  int resource;
    private Context context;
    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        this.moviesList = objects;
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater linflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= linflater.inflate(R.layout.row, parent, false);
        TextView movie   = (TextView) row.findViewById(R.id.movie);
        TextView tagline = (TextView) row.findViewById(R.id.tagline);
        TextView year    = (TextView) row.findViewById(R.id.year);
        TextView duration= (TextView) row.findViewById(R.id.duration);
        TextView director= (TextView) row.findViewById(R.id.director);
        TextView story   = (TextView) row.findViewById(R.id.story);
        ImageView image  = (ImageView)row.findViewById(R.id.image);
        RatingBar rating = (RatingBar)row.findViewById(R.id.rating);
        TextView  cast   = (TextView) row.findViewById(R.id.cast);
        final ProgressBar progressBar = (ProgressBar) row.findViewById(R.id.progressBar);

        movie.setText(moviesList.get(position).getMovie());
        tagline.setText(moviesList.get(position).getTagline());
        year.setText("Year:"+moviesList.get(position).getYear());
        duration.setText("Duration:"+moviesList.get(position).getDuration());
        director.setText("Director:"+moviesList.get(position).getDirector());
        story.setText(moviesList.get(position).getStory());
        StringBuffer castString = new StringBuffer();
        for (int i=0;i<moviesList.get(position).getCastList().size();i++ ) {
            castString.append(moviesList.get(position).getCastList().get(i).getName() + "  ");
        }
        cast.setText(castString);
        rating.setRating(moviesList.get(position).getRating()/2);
        rating.setEnabled(false);

        ImageLoader.getInstance().displayImage(moviesList.get(position).getImage(), image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
            }
        });

        return row ;
    }
}


