package edu.truman.android.hackmidwest;

import android.support.v4.app.Fragment;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MajorSpinnerFragment();
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }
}