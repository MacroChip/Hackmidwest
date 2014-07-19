package edu.truman.android.hackmidwest.main_screen_view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.company_list_view.CompanyListActivity;
import edu.truman.android.hackmidwest.models.Company;
import roboguice.fragment.RoboFragment;

public class MajorSpinnerFragment extends RoboFragment {
    private Button submitCompanyButton;
    private Spinner spinner;
    ArrayAdapter adapter;

    List<String> majors = new ArrayList<String>();
    Map<String, List<Company>> majorToCompanyMap = new HashMap<String, List<Company>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_spinner_major, container, false);

        populateMajors(majors);
        spinner = (Spinner) view.findViewById(R.id.spinner_major);
        populateSpinner(spinner, majors);
        Log.d("SPINNER SELECTED", spinner.getSelectedItem().toString());

        submitCompanyButton = (Button) view.findViewById(R.id.spinner_major_submit);
        submitCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MajorsDialog dialog = new MajorsDialog();



                Intent i = new Intent(getActivity(), CompanyListActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void populateSpinner(Spinner spinner, List<String> majors) {
        String array_spinner[];
        array_spinner = new String[majors.size()];
        for (int i = 0; i < majors.size(); i++) {
            array_spinner[i] = majors.get(i);
        }
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, array_spinner);
        spinner.setAdapter(adapter);
    }

    private void populateMajors(List<String> majors) {
        majors.add("computer science");
        ArrayList<Company> csCompanies = new ArrayList<Company>();
        csCompanies.add(new Company("CERNER", 15));
        csCompanies.add(new Company("ASYNCHRONY", 25));
        csCompanies.add(new Company("BOEING", 35));
        majorToCompanyMap.put("computer science", csCompanies);

        majors.add("accounting");
        ArrayList<Company> accCompanies = new ArrayList<Company>();
        accCompanies.add(new Company("EARNST AND YOUNG", 5));
        accCompanies.add(new Company("KPMG", 15));
        accCompanies.add(new Company("EDWARD JONES", 25));
        majorToCompanyMap.put("accounting", accCompanies);


        majors.add("biology");
        ArrayList<Company> bioCompanies = new ArrayList<Company>();
        accCompanies.add(new Company("MONSANTO", 155));
        accCompanies.add(new Company("ALDI", 135));
        accCompanies.add(new Company("LOGAN UNIVERSITY", 245));
        majorToCompanyMap.put("accounting", bioCompanies);

        majors.add("business");
        majors.add("chemistry");
        majors.add("physics");
        majors.add("mathematics");
    }
}
