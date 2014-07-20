package edu.truman.android.hackmidwest.company_list_view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;

import java.util.List;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.main_screen_view.MajorFragment;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import edu.truman.android.hackmidwest.single_company_view.SingleCompanyActivity;
import edu.truman.android.hackmidwest.single_company_view.SingleCompanyViewFragment;
import roboguice.fragment.RoboListFragment;

public class CompanyListFragment extends RoboListFragment {
    public static final String LIST = "some list";
    @Inject
    ExperienceEntry experienceEntry;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        CompanyAdapter adapter = new CompanyAdapter(MajorFragment.listToBeSent);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ExperienceEntry company = ((CompanyAdapter)getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), SingleCompanyActivity.class);
        i.putExtra(SingleCompanyViewFragment.COMPANY_KEY, company);
        startActivity(i);
    }

    private class CompanyAdapter extends ArrayAdapter<ExperienceEntry> {
        public CompanyAdapter(List<ExperienceEntry> companyList) {
            super(getActivity(), 0, companyList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.company_list_view, null);
                holder.companyTitleTextView = (TextView) convertView.findViewById(R.id.company_title_text_view);
                holder.companySalaryTextView = (TextView) convertView.findViewById(R.id.company_salary_text_view);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ExperienceEntry company = getItem(position);
            holder.companyTitleTextView.setText(company.getName());
            return convertView;
        }

        private class ViewHolder {
            TextView companyTitleTextView;
            TextView companySalaryTextView;
        }
    }
}
