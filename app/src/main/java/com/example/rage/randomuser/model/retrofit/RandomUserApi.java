package com.example.rage.randomuser.model.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.rage.randomuser.model.retrofit.pojo.Users;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rage on 15.03.2017.
 */

public class RandomUserApi {

    private final String API_URL = "https://randomuser.me/";
    private ApiCallRandomUser apiCallRandomUser;
    private static RandomUserApi randomUserApi;

    private RandomUserApi() {
        init();
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiCallRandomUser = retrofit.create(ApiCallRandomUser.class);

    }

    public static RandomUserApi getInstance() {
        if (randomUserApi == null) {
            randomUserApi = new RandomUserApi();
        }
        return randomUserApi;
    }

    public Response<Users> getUsersList(int countUser) throws IOException {
        Call<Users> userCall = apiCallRandomUser.getUsers(countUser);
        Response<Users> userResponse = userCall.execute();
        return userResponse;
    }
}
