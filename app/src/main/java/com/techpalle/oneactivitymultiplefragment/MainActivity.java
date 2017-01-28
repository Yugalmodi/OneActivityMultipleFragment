package com.techpalle.oneactivitymultiplefragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String str;
    LoginFragment loginFragment;
    WelcomeFragment welcomeFragment;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = new LoginFragment();
        welcomeFragment = new WelcomeFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //for handling auto-rotation
        if(savedInstanceState == null){
        transaction.add(R.id.container1, loginFragment);
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                transaction.add(R.id.container2, welcomeFragment);
            }
        }
            //for handling app for mobile and tablets
            //if user is in portrait mode add only one fragment
            //else add two fragment

        else {
                transaction.replace(R.id.container1, loginFragment);
            }
            transaction.commit();
        }

    public void loginButtonClicked(String s){
        welcomeFragment = new WelcomeFragment();
        Bundle b = new Bundle();
        b.putString("key",s);
        str = s;
        welcomeFragment.setArguments(b);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.container2, welcomeFragment, "w");
        }else {
            transaction.replace(R.id.container1, welcomeFragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
