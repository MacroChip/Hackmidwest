package edu.truman.android.hackmidwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Company;
import models.CompanyBank;

public class MajorSpinnerFragment extends Fragment {

    @Inject
    CompanyBank companyBank;

    private Spinner spinner;
    private Button submitCompanyButton;
    String[] testCompanies = new String[] {"CERNER", "BOEING", "ASYNCHRONY"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_spinner_major, container, false);
        spinner = (Spinner) view.findViewById(R.id.spinner_major);
        submitCompanyButton = (Button) view.findViewById(R.id.spinner_major_submit);
        submitCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CompanyListActivity.class);
                startActivity(i);
            }
        });

        setCompanyList();
        return view;
    }

    private void setCompanyList() {
        List<Company> companyList = new ArrayList<Company>();
        for (int i = 0; i < 60; i++) {
            companyList.add(new Company(testCompanies[new Random().nextInt(2)], i));
        }
        companyBank.setCompanyList(companyList);
    }
}
