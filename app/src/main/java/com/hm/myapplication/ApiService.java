package com.hm.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/?ext")
    Call<ResponseBody> getSingleUser();

    @GET("api/?ext")
    Call<ResponseBody> getUsersList(@Query("amount") int amount);
}
