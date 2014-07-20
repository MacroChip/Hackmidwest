package edu.truman.android.hackmidwest.single_company_view;


import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import roboguice.fragment.RoboFragment;

public class SingleCompanyActivity extends SingleFragmentActivity {

    ExperienceEntry company;

    @Override
    protected RoboFragment createFragment() {
        company = (ExperienceEntry) getIntent().getSerializableExtra(SingleCompanyViewFragment.COMPANY_KEY);
        return SingleCompanyViewFragment.newInstance(company);
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }
}
