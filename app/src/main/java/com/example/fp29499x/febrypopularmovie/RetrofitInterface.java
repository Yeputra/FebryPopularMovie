package com.example.fp29499x.febrypopularmovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by FP29499X on 7/9/2017.
 */

public interface RetrofitInterface {
    public static final String API_KEY = "YOUR_OWN_API_KEY";
    public static final String BASE_IMAGE = "https://image.tmdb.org/t/p/w185";

    @GET("movie/popular")
    Call<Movies> getMoviePopular(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<Movies> getMovieTopRated(@Query("api_key") String apikey);

}
