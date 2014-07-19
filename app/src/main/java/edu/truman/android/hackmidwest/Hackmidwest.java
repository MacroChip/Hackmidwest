package edu.truman.android.hackmidwest;

import android.app.Application;

import com.google.inject.Module;

import roboguice.RoboGuice;

/**
 * Created by chip on 7/19/14.
 */
public class Hackmidwest extends Application {

    private Module module = new ApplicationModule(this);

    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), module);
    }
}
