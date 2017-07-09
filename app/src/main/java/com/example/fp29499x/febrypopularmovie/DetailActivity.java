package com.example.fp29499x.febrypopularmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.GsonBuilder;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Result data = new GsonBuilder().create().fromJson(this.getIntent().getStringExtra("movie"), Result.class);

        ImageView mDetailImage = (ImageView) findViewById(R.id.detailimage);
        TextView mTittleMovie = (TextView) findViewById(R.id.tittle_movie);
        TextView mReleaseDateMovie = (TextView) findViewById(R.id.releasedate_movie);
        TextView mRatingMovie = (TextView) findViewById(R.id.rating_movie);
        TextView mSynopsisMovie = (TextView) findViewById(R.id.synopsis_movie);


        mTittleMovie.setText(data.getTitle());
        mReleaseDateMovie.setText(data.getReleaseDate());
        mRatingMovie.setText(""+data.getVoteAverage());
        mSynopsisMovie.setText(data.getOverview());
        Glide.with(this)
                .load(RetrofitInterface.BASE_IMAGE + data.getPosterPath())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(mDetailImage);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        untuk bikin back button di atas
        if (item.getItemId() == android.R.id.home){
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
