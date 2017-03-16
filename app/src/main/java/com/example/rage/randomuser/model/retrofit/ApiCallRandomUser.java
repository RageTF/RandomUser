package com.example.rage.randomuser.model.retrofit;

import com.example.rage.randomuser.model.retrofit.pojo.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rage on 15.03.2017.
 */

public interface ApiCallRandomUser {

    @GET("api")
    Call<Users> getUsers(@Query("results") int countUser);

}
