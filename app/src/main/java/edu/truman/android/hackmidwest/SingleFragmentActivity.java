package edu.truman.android.hackmidwest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import roboguice.activity.RoboFragmentActivity;
import roboguice.fragment.RoboFragment;


public abstract class SingleFragmentActivity extends RoboFragmentActivity{
    protected abstract RoboFragment createFragment();
    protected abstract int getContainerResource();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(getContainerResource(), createFragment()).commit();
    }
}
