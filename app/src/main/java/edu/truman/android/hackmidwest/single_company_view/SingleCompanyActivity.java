package edu.truman.android.hackmidwest.single_company_view;


import android.support.v4.app.Fragment;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import edu.truman.android.hackmidwest.models.Company;

public class SingleCompanyActivity extends SingleFragmentActivity {

    Company company;

    @Override
    protected Fragment createFragment() {
        company = (Company) getIntent().getSerializableExtra(SingleCompanyViewFragment.COMPANY_KEY);
        return SingleCompanyViewFragment.newInstance(company);
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }
}
