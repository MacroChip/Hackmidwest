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

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import roboguice.fragment.RoboFragment;

public class SingleCompanyViewFragment extends RoboFragment {

    @Inject
    private ExperienceBank experienceBank;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if(NavUtils.getParentActivityName(getActivity()) != null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        View view =  inflater.inflate(R.layout.single_company_fragment_view, null);

        companyTitleTextView = (TextView) view.findViewById(R.id.company_title_single_page_view);
        company = (ExperienceEntry) getArguments().getSerializable(COMPANY_KEY);
        StringBuilder stringBuilder = new StringBuilder();
        if (experienceBank.getCompanies().size() > 0) {
            stringBuilder.append("Looking for majors: \n");
        }
        for (ExperienceEntry experienceEntry : experienceBank.getCompanies()) {
            if (experienceEntry.getName().equals(company.getName())) {
                for (String major : experienceEntry.getMajors()) {
                    stringBuilder.append(major);
                    stringBuilder.append("\n");
                }
            }
        }
        stringBuilder.append(company.toString());
        companyTitleTextView.setText(stringBuilder.toString());
        return view;
    }
}
