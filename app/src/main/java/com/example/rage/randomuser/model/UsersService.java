package com.example.rage.randomuser.model;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.rage.randomuser.modelview.UserListViewModel;

/**
 * Created by Rage on 15.03.2017.
 */

public class UsersService extends IntentService {

    public static final String KEY_SERVICE_USERS="KEY_LOAD_USERS";

    public static final int LOAD=1;
    public static final int CLEAR=2;

    public UsersService() {
        super("UsersService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        UsersModel usersModel = new UsersModel(getApplicationContext());
        int action=intent.getIntExtra(KEY_SERVICE_USERS,-1);
        if(action==LOAD) {
            Log.v("LISTACT","SERVICE");
            usersModel.loadUsers();
            Intent updateIntent = new Intent(UserListViewModel.BROADCAST_ACTION);
            sendBroadcast(updateIntent);
        }else if(action==CLEAR){
            usersModel.clearCache();
            stopSelf();
        }
    }
}
