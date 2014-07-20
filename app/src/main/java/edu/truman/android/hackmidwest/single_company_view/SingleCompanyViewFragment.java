package edu.truman.android.hackmidwest.single_company_view;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.inject.Inject;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.events.GlassdoorTaskCompleteEvent;
import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import edu.truman.android.hackmidwest.tasks.GlassdoorTask;
import roboguice.fragment.RoboFragment;

public class SingleCompanyViewFragment extends RoboFragment {

    @Inject
    private ExperienceBank experienceBank;
    @Inject
    private Bus bus;
    @Inject
    private CompanyBank companyBank;

    public static final String COMPANY_KEY = "company model";
    private TextView companyTitleTextView;
    private ExperienceEntry company;

    public static RoboFragment newInstance(ExperienceEntry company) {
        Bundle args = new Bundle();
        args.putSerializable(COMPANY_KEY, company);

        SingleCompanyViewFragment fragment = new SingleCompanyViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityIntent(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        company = (ExperienceEntry) getArguments().getSerializable(COMPANY_KEY);
        new GlassdoorTask(getActivity(), company.getName()).execute();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if(NavUtils.getParentActivityName(getActivity()) != null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        View view =  inflater.inflate(R.layout.single_company_fragment_view, null);

        companyTitleTextView = (TextView) view.findViewById(R.id.company_title_single_page_view);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(company.toString());
        companyTitleTextView.setText(stringBuilder.toString());
        return view;
    }

    @Subscribe
    public void onGlassdoorTaskComplete(GlassdoorTaskCompleteEvent e) {
        companyTitleTextView.setText(companyTitleTextView.getText() +
                companyBank.getCompanyList().get(0).toString());
    }
}
