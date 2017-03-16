package com.example.rage.randomuser.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.rage.randomuser.model.retrofit.RandomUserApi;
import com.example.rage.randomuser.model.retrofit.pojo.Result;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rage on 15.03.2017.
 */

public class UsersModel {

    public static final String USERS_DATA="USERS_PREFERENCES";

    private RandomUserApi randomUserApi;
    private Context context;

    public UsersModel(Context context){
        this.context=context;
        randomUserApi=RandomUserApi.getInstance();
    }

    public void loadUsers(){
        try {
            SharedPreferences sharedPreferences=context.getSharedPreferences(USERS_DATA,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            List<Result> results=randomUserApi.getUsersList(100).body().getResults();
            for(Result result:results){
                User user=new User(result);
                editor.putString(user.getId().toString(),user.toJson());
            }
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clearCache(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(USERS_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
