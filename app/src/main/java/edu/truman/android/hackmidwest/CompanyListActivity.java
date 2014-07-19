package edu.truman.android.hackmidwest;

import android.support.v4.app.Fragment;

public class CompanyListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CompanyListFragment();
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }
}
