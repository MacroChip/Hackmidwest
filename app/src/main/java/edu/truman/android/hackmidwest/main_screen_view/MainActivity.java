package edu.truman.android.hackmidwest.main_screen_view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.google.inject.Inject;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.tasks.ExperienceTask;
import edu.truman.android.hackmidwest.tasks.MajorsTask;
import roboguice.activity.RoboFragmentActivity;


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
        new ExperienceTask(this).execute();
        //populate majorsBank
        new MajorsTask(this).execute();
    }
}
