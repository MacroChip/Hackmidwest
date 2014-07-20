package edu.truman.android.hackmidwest.company_list_view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import edu.truman.android.hackmidwest.R;
import roboguice.activity.RoboFragmentActivity;

public class CompanyListActivity extends RoboFragmentActivity {
    public static final String LIST = "some list";
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_layout);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragmentContainer, new CompanyListFragment()).commit();
    }
}
