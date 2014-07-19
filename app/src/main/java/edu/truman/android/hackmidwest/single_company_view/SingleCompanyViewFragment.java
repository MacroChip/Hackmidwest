package edu.truman.android.hackmidwest.single_company_view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.models.Company;

public class SingleCompanyViewFragment extends Fragment {

    public static final String COMPANY_KEY = "company model";
    private TextView companyTitleTextView;
    private Company company;

    public static Fragment newInstance(Company company) {
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
        Log.d("BOOM", company.getCompanyName());
        Log.d("BOOM", company.getSalary() + "");
        companyTitleTextView.setText(company.getCompanyName() + " Salary: " + company.getSalary());
        return view;
    }
}
