package com.hm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hm.myapplication.models.UserModel;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://uinames.com/")
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    @Override
    protected void onStart() {

        super.onStart();

        getSingleUser();
    }

    private void getSingleUser() {

        Call<ResponseBody> call = apiService.getSingleUser();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONObject responseJson = getJsonResponse(response);

                UserModel userModel = new UserModel(responseJson);

                System.out.println(userModel);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println(t.getMessage());
            }
        });
    }

    private JSONObject getJsonResponse(Response<ResponseBody> response) {

        ResponseBody responseBody = response.body();

        if(responseBody == null) //failure occurred
            responseBody = response.errorBody();

        JSONObject responseJson = null;

        try {

            responseJson = new JSONObject(responseBody.string());
        } catch (Exception e) {

            e.printStackTrace();
        }

        return responseJson;
    }
}
