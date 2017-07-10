package com.example.fp29499x.febrypopularmovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.GsonBuilder;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by FP29499X on 7/9/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    List<Result> movieList;

    public MovieAdapter(List<Result> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptermovies, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.movietittle.setText(movieList.get(position).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(RetrofitInterface.BASE_IMAGE + movieList.get(position).getPosterPath())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(holder.movieimage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setData(List<Result> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView movietittle;
        ImageView movieimage;
        CardView moviecardview;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movietittle = (TextView) itemView.findViewById(R.id.movietittle);
            movieimage = (ImageView) itemView.findViewById(R.id.movieimage);
            moviecardview = (CardView) itemView.findViewById(R.id.moviecardview);

            moviecardview.setOnClickListener(this);

            movietittle.setSelected(true);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Result data = movieList.get(position);
            final Context context = v.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("movie",new GsonBuilder().create().toJson(data));
            context.startActivity(intent);
        }
    }
}
