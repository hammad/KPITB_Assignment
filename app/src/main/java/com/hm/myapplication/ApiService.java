package com.hm.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/")
    Call<ResponseBody> getSingleUser();
}
