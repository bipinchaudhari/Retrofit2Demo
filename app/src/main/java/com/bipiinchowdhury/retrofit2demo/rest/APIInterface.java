package com.bipiinchowdhury.retrofit2demo.rest;

import com.bipiinchowdhury.retrofit2demo.model.Example;
import com.bipiinchowdhury.retrofit2demo.utility.DContants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Bipiin.chowdhury on 02-06-2017.
 */

public interface APIInterface {
    @GET(DContants.DEMO_URL)
    Call<Example> fetchDemoAPI();
}
