package com.hm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.myapplication.models.UserModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ApiService apiService;

    private TextView usernameTV, surnameTV, genderTV, regionTV;
    private ImageView userIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTV = (TextView) findViewById(R.id.username_text_view);
        surnameTV = (TextView) findViewById(R.id.surname_text_view);
        genderTV = (TextView) findViewById(R.id.gender_text_view);
        regionTV = (TextView) findViewById(R.id.region_text_view);
        userIV = (ImageView) findViewById(R.id.user_image_view);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://uinames.com/")
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    @Override
    protected void onStart() {

        super.onStart();

        getSingleUser();

        //getUsersList(10);
    }

    private void updateUIWithUser(UserModel user) {

        Picasso.get().load(user.photoUrl).into(userIV);

        usernameTV.setText(user.name);
        surnameTV.setText(user.surname);
        genderTV.setText(user.gender);
        regionTV.setText(user.region);
    }

    private void getSingleUser() {

        Call<ResponseBody> call = apiService.getSingleUser();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONObject responseJson = getJsonResponse(response);

                UserModel userModel = new UserModel(responseJson);

                updateUIWithUser(userModel);

                System.out.println(userModel);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println(t.getMessage());
            }
        });
    }

    private void getUsersList(int amount) {

        Call<ResponseBody> call = apiService.getUsersList(amount);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONArray responseObject = getJsonArrayResponse(response);

                ArrayList<UserModel> users = new ArrayList<>();

                for(int i=0; i<responseObject.length(); i++) {

                    try {

                        JSONObject jsonObject = responseObject.getJSONObject(i);

                        UserModel userModel = new UserModel(jsonObject);

                        users.add(userModel);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                }

                System.out.println(users);
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

    private JSONArray getJsonArrayResponse(Response<ResponseBody> response) {

        ResponseBody responseBody = response.body();

        if(responseBody == null) //failure occurred
            responseBody = response.errorBody();

        JSONArray responseJson = null;

        try {

            responseJson = new JSONArray(responseBody.string());
        } catch (Exception e) {

            e.printStackTrace();
        }

        return responseJson;
    }
}
