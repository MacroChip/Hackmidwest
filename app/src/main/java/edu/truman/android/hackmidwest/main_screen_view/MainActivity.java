package edu.truman.android.hackmidwest.main_screen_view;

import android.support.v4.app.Fragment;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;


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
