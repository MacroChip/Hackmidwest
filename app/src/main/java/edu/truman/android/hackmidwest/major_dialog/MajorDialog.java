package edu.truman.android.hackmidwest.major_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.company_list_view.CompanyListActivity;
import edu.truman.android.hackmidwest.company_list_view.CompanyListFragment;
import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import edu.truman.android.hackmidwest.models.MajorsBank;
import roboguice.fragment.RoboDialogFragment;

public class MajorDialog extends RoboDialogFragment {

    @Inject
    MajorsBank majorsBank;
    @Inject
    ExperienceBank experienceBank;

    ArrayList<ExperienceEntry> majorsList = new ArrayList<ExperienceEntry>();
    HashMap<String, ArrayList<Company>> majorToCompanyMap = new HashMap<String, ArrayList<Company>>();

    List<String> majors = new ArrayList<String>();

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
//        populateMajors(majorsBank.getMajors(), experienceBank.getCompanies())
            populateMajors(majors);
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.majors_list_view, null);
        ListView lv = (ListView) convertView.findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, majorsBank.getMajors());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String major = (String) parent.getItemAtPosition(position);
                Company company = majorToCompanyMap.get(major).get(0);
                Toast.makeText(getActivity(), company.getName(), Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MajorDialog.this, CompanyListActivity.class);
//                i.putExtra(CompanyListFragment.LIST, company);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle("Majors")
                .setView(convertView)
                .setPositiveButton(android.R.string.ok, null)
                .create();
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

//
//    private void populateMajors(List<String> majors, List<ExperienceEntry> experience) {
//        majorsList.clear();
//        for (String major : majors) {
//            for (ExperienceEntry entry : experience) {
//                if (entry.getMajors().contains(major)) {
//                    majorsList.add(entry);
//                }
//            }
//            majorToCompanyMap.put(major, majorsList);
//            majorsList.clear();
//        }
//    }
}
