package com.flashpoint.ticktocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;

import java.io.StringBufferInputStream;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;
    private String user;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        showFragment(new LoginFragment());
    }

    public void showFragment(Fragment fragment) {
        currentFragment = fragment;

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (!(currentFragment instanceof CalendarFragment)) {
            goBack();
        }
    }


    public void setUser(String a) {
        a = a.replace(".", "");
        user = a;
    }

    public String getUser() {
        return user;
    }

    public void setDay(int a)
    {
        day = a;
    }

    public int getDay()
    {
        return day;
    }

    public void setMonth(int a)
    {
        month = a;
    }

    public int getMonth()
    {
        return month;
    }

    public void setYear(int a)
    {
        year = a;
    }

    public int getYear()
    {
        return year;
    }
}




