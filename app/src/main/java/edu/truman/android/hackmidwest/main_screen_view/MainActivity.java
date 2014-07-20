package edu.truman.android.hackmidwest.main_screen_view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.google.inject.Inject;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import edu.truman.android.hackmidwest.company_list_view.CompanyListFragment;
import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.tasks.ExperienceTask;
import edu.truman.android.hackmidwest.tasks.GlassdoorTask;
import edu.truman.android.hackmidwest.tasks.MajorsTask;
import roboguice.activity.RoboActivity;
import roboguice.activity.RoboFragmentActivity;
import roboguice.activity.RoboListActivity;
import roboguice.fragment.RoboFragment;

import static java.lang.Thread.sleep;


public class MainActivity extends RoboFragmentActivity {
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
        setContentView(R.layout.activity_layout);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragmentContainer, new MajorFragment()).commit();
    }

    private void setCompanyList() {
        //populate ExperienceBank
        new ExperienceTask(this, schoolToSearch).execute();
        //populate CompanyBank
        new GlassdoorTask(this, companyToSearch).execute();
        //populate majorsBank
        new MajorsTask(this).execute();
    }
}
