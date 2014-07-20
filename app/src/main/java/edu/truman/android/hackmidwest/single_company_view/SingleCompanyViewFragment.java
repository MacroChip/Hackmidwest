package edu.truman.android.hackmidwest.single_company_view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.inject.Inject;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import roboguice.fragment.RoboFragment;

public class SingleCompanyViewFragment extends RoboFragment {

    @Inject
    private ExperienceBank experienceBank;

    public static final String COMPANY_KEY = "company model";
    private TextView companyTitleTextView;
    private Company company;

    public static RoboFragment newInstance(ExperienceEntry company) {
        Bundle args = new Bundle();
        args.putSerializable(COMPANY_KEY, company);

        SingleCompanyViewFragment fragment = new SingleCompanyViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.single_company_fragment_view, null);
        companyTitleTextView = (TextView) view.findViewById(R.id.company_title_single_page_view);
        company = (Company) getArguments().getSerializable(COMPANY_KEY);
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
