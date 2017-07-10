package com.example.fp29499x.febrypopularmovie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainmovielist)
    RecyclerView mMainMovieList;
    private List<Result> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        GridLayoutManager --> size sama semua
//        StaggeredGridLayoutManager --> size beda2 sesuai ukuran gambar

        mMainMovieList.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        movieAdapter = new MovieAdapter(movieList);
        mMainMovieList.setAdapter(movieAdapter);
        mMainMovieList.setHasFixedSize(true);

        actionPopular();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
                actionPopular();
                break;
            case R.id.action_toprated:
                actionTopRated();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionTopRated() {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                RetrofitInterface retrofitInterface = Network.getRetrofit().create(RetrofitInterface.class);
                Call<Movies> movie = retrofitInterface.getMovieTopRated(BuildConfig.THE_MOVIE_DB_API);
                movie.enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        Log.d(MainActivity.class.getSimpleName(), "onResponse: ");
                        movieList.clear();
                        movieList.addAll(response.body().getResults());
                        movieAdapter.notifyDataSetChanged();
                        // Toast.makeText(MainActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        Log.e(MainActivity.class.getSimpleName(), "onFailure: ");
                        // Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                return "";
            }
        }.execute();
    }

    private void actionPopular() {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                RetrofitInterface retrofitInterface = Network.getRetrofit().create(RetrofitInterface.class);
                Call<Movies> movie = retrofitInterface.getMoviePopular(BuildConfig.THE_MOVIE_DB_API);
                movie.enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        Log.d(MainActivity.class.getSimpleName(), "onResponse: ");
                        movieList.clear();
                        movieList.addAll(response.body().getResults());
                        movieAdapter.notifyDataSetChanged();
                        // Toast.makeText(MainActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {
                        Log.e(MainActivity.class.getSimpleName(), "onFailure: ");
                        // Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                return "";
            }
        }.execute();
    }
}
