package edu.truman.android.hackmidwest.company_list_view;

import android.support.v4.app.Fragment;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;

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
