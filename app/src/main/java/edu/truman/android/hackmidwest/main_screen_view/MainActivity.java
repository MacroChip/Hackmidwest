package edu.truman.android.hackmidwest.main_screen_view;

import android.os.Bundle;

import com.google.inject.Inject;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.tasks.ExperienceTask;
import edu.truman.android.hackmidwest.tasks.GlassdoorTask;
import roboguice.fragment.RoboFragment;


public class MainActivity extends SingleFragmentActivity {
    @Inject
    CompanyBank companyBank;
    @Inject
    ExperienceBank experienceBank;

    private String companyToSearch = null;
    private String schoolToSearch = null;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setCompanyList();
    }
    @Override
    protected RoboFragment createFragment() {
        return new MajorSpinnerFragment();
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
    }

    private void setCompanyList() {
        //populate ExperienceBank
        new ExperienceTask(this, schoolToSearch).execute();
        //populate CompanyBank
        new GlassdoorTask(this, companyToSearch).execute();
    }
}
