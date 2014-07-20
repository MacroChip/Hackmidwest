package edu.truman.android.hackmidwest.major_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothClass;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    public static List<ExperienceEntry> listToBeSent;
    Map<String, Integer> mapIdToKey;
    Map<Integer, List<ExperienceEntry>> majorToCompanyMap = new HashMap<Integer, List<ExperienceEntry>>();

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
//        populateMajors(majorsBank.getMajors(), experienceBank.getCompanies())
//            populateMajors(majors);
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        mapMajorsToKeys();
        populateMajors(majorsBank.getMajors(), experienceBank.getCompanies());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.majors_list_view, null);
        ListView lv = (ListView) convertView.findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, majorsBank.getMajors());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String major = (String) parent.getItemAtPosition(position);
                Log.d("DIALOG", major);
                Log.d("DIALOG", mapIdToKey.get(major) + "");
                int majorId = mapIdToKey.get(major);
                listToBeSent = majorToCompanyMap.get(majorId);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle("Majors")
                .setView(convertView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(getActivity(), CompanyListActivity.class);
                        startActivity(intent);
                    }
                })
                .create();
    }


    private void mapMajorsToKeys() {
        List<String> majors = majorsBank.getMajors();
        mapIdToKey = new HashMap<String, Integer>();
        int i = 0;
        for (String major : majors) {
            mapIdToKey.put(major, i);
            i++;
        }

    }

    private void populateMajors(List<String> majors, List<ExperienceEntry> experience) {
        for (String major : majors) {
            List<ExperienceEntry> experienceCompanyList = new ArrayList<ExperienceEntry>();
            for (ExperienceEntry entry : experience) {
                if (entry.getMajors().contains(major)) {
                    experienceCompanyList.add(entry);
                }
            }
            majorToCompanyMap.put(mapIdToKey.get(major), experienceCompanyList);
        }
    }
}
