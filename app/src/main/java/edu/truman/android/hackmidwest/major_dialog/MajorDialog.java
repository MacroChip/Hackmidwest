package edu.truman.android.hackmidwest.major_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
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

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.models.Company;
import roboguice.fragment.RoboDialogFragment;

public class MajorDialog extends RoboDialogFragment {

    List<String> majorsList = new ArrayList<String>();
    Map<String, List<Company>> majorToCompanyMap = new HashMap<String, List<Company>>();

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        majorsList.add("computer science");
        majorsList.add("accounting");
        majorsList.add("biology");
        majorsList.add("business");
        majorsList.add("chemistry");
        majorsList.add("physics");
        majorsList.add("mathematics");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.majors_list_view, null);
        ListView lv = (ListView) convertView.findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, majorsList);
//        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String major = (String) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),major, Toast.LENGTH_SHORT).show();
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
}
