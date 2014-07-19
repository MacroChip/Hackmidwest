package edu.truman.android.hackmidwest.single_company_view;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import edu.truman.android.hackmidwest.models.Company;
import roboguice.fragment.RoboFragment;

public class SingleCompanyActivity extends SingleFragmentActivity {

    Company company;

    @Override
    protected RoboFragment createFragment() {
        company = (Company) getIntent().getSerializableExtra(SingleCompanyViewFragment.COMPANY_KEY);
        return SingleCompanyViewFragment.newInstance(company);
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }
}
