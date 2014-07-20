package edu.truman.android.hackmidwest.main_screen_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.company_list_view.CompanyListActivity;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import edu.truman.android.hackmidwest.models.MajorsBank;
import roboguice.fragment.RoboListFragment;

public class MajorFragment extends RoboListFragment {

    @Inject
    MajorsBank majorsBank;
    @Inject
    ExperienceBank experienceBank;
    public static List<ExperienceEntry> listToBeSent;
    Map<String, Integer> mapIdToKey;
    Map<Integer, List<ExperienceEntry>> majorToCompanyMap = new HashMap<Integer, List<ExperienceEntry>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapMajorsToKeys();
        populateMajors(majorsBank.getMajors(), experienceBank.getCompanies());
        MajorAdapter adapter = new MajorAdapter(majorsBank.getMajors());
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String major = ((MajorAdapter) getListAdapter()).getItem(position);
        int majorId = mapIdToKey.get(major);
        listToBeSent = majorToCompanyMap.get(majorId);
        Intent intent = new Intent(getActivity(), CompanyListActivity.class);
        startActivity(intent);
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

    private class MajorAdapter extends ArrayAdapter<String> {
        public MajorAdapter(List<String> majors) {
            super(getActivity(), 0, majors);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getActivity().getLayoutInflater().inflate(R.layout.majors_list_view, null);
            TextView majorTitleView = (TextView) convertView.findViewById(R.id.major_title);
            String majorTitle = getItem(position);
            majorTitleView.setText(majorTitle);

            return convertView;
        }
    }
}