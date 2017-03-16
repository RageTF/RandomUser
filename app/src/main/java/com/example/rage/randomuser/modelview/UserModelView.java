package com.example.rage.randomuser.modelview;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.example.rage.randomuser.model.User;
import com.example.rage.randomuser.model.UsersModel;
import com.example.rage.randomuser.view.UserActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Rage on 15.03.2017.
 */

public class UserModelView {

    private User user;

    public UserModelView(User user){
        this.user=user;
    }


    public String getName(){
        return user.getName();
    }

    public String getGender(){
        return user.getGender();
    }

    public String getCity(){
        return user.getCity();
    }

    public String getPhone(){
        return user.getPhone();
    }

    public String getPhoto(){
        return user.getPhoto();
    }

    public String getEmail(){
        return user.getEmail();
    }

    @BindingAdapter("android:src")
    public static void setPhoto(ImageView imageView,String url){
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

}
