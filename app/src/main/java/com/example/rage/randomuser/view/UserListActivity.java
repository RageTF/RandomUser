package com.example.rage.randomuser.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rage.randomuser.R;
import com.example.rage.randomuser.databinding.ActivityUserListBinding;
import com.example.rage.randomuser.databinding.ItemUserBinding;
import com.example.rage.randomuser.model.User;
import com.example.rage.randomuser.model.UsersModel;
import com.example.rage.randomuser.model.UsersService;
import com.example.rage.randomuser.modelview.UserItemViewModel;
import com.example.rage.randomuser.modelview.UserListViewModel;

import java.util.Observable;
import java.util.Observer;

public class UserListActivity extends AppCompatActivity implements Observer {

    private UserAdapter userAdapter;
    private ActivityUserListBinding activityUserListBinding;
    private UserListViewModel userListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSharedPreferences(UsersModel.USERS_DATA,MODE_PRIVATE).edit().clear().commit();
        initDataBinding();
        initUserList(activityUserListBinding.userList);
        startService(new Intent(this, UsersService.class).putExtra(UsersService.KEY_SERVICE_USERS,UsersService.LOAD));
    }

    private void initDataBinding(){
        activityUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        userListViewModel=new UserListViewModel(this);
        userListViewModel.addObserver(this);
    }

    private void initUserList(RecyclerView recyclerView){
        userAdapter=new UserAdapter();
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.v("LISTACT","UPDATE");
        userAdapter.setUsers(userListViewModel.getUsers());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, UsersService.class).putExtra(UsersService.KEY_SERVICE_USERS,UsersService.CLEAR));
        userListViewModel.unregister();
    }
}
