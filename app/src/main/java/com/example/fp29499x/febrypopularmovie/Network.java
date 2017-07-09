package com.example.fp29499x.febrypopularmovie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FP29499X on 7/9/2017.
 */

public class Network {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
