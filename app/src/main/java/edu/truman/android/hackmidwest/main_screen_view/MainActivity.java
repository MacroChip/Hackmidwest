package edu.truman.android.hackmidwest.main_screen_view;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import roboguice.fragment.RoboFragment;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected RoboFragment createFragment() {
        return new MajorSpinnerFragment();
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }
}
