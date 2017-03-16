package com.example.rage.randomuser.modelview;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.example.rage.randomuser.model.User;
import com.squareup.picasso.Picasso;

/**
 * Created by Rage on 15.03.2017.
 */

public class UserItemViewModel extends BaseObservable {

    private User user;

    public UserItemViewModel(User user){
        this.user=user;
    }

    public String getName(){
        return user.getName();
    }

    public String getPhoto(){
        return user.getPhoto();
    }

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }

    @BindingAdapter({"android:src"})
    public static void setPhoto(ImageView imageView,String urlImage){
        Picasso.with(imageView.getContext()).load(urlImage).into(imageView);
    }
}
