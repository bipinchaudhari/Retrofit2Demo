package com.bipiinchowdhury.retrofit2demo.rest;

import com.bipiinchowdhury.retrofit2demo.utility.DContants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bipiin.chowdhury on 02-06-2017.
 */

public class APIClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(DContants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
