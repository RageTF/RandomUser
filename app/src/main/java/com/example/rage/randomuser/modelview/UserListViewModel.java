package com.example.rage.randomuser.modelview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.rage.randomuser.model.User;
import com.example.rage.randomuser.model.UsersModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Rage on 15.03.2017.
 */

public class UserListViewModel extends Observable {

    public static final String BROADCAST_ACTION="com.rage.modelview.UserListModelView";
    private Context context;
    private UsersReceiver usersReceiver;

    public UserListViewModel(Context context){
        this.context=context;
        usersReceiver=new UsersReceiver();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        context.registerReceiver(usersReceiver,intentFilter);
    }

    public List<User> getUsers(){
        List<User> usersList=new ArrayList<>();
        Gson gson=new Gson();
        SharedPreferences sharedPreferences=context.getSharedPreferences(UsersModel.USERS_DATA,Context.MODE_PRIVATE);
        Map<String,String> users= (Map<String, String>) sharedPreferences.getAll();
        for(Map.Entry<String,String> userEntry:users.entrySet()){
            try {
                JSONObject jsonObject=new JSONObject(userEntry.getValue());
                User user=new User();
                user.setName(jsonObject.getString("name"));
                user.setGender(jsonObject.getString("gender"));
                user.setCity(jsonObject.getString("city"));
                user.setEmail(jsonObject.getString("email"));
                user.setPhone(jsonObject.getString("phone"));
                user.setPhoto(jsonObject.getString("photo"));
                usersList.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return usersList;
    }

    public void unregister(){
        context.unregisterReceiver(usersReceiver);
    }

    private class UsersReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("LISTACT","RECEICE");
            setChanged();
            notifyObservers();
        }
    }

}
