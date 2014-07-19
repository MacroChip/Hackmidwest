package edu.truman.android.hackmidwest;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;

import java.util.List;

import models.Company;
import models.CompanyBank;

public class CompanyListFragment extends ListFragment {

    CompanyBank companyBank;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        companyBank = CompanyBank.getInstance(getActivity());
        CompanyAdapter adapter = new CompanyAdapter(companyBank.getCompanyList());
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Company company = ((CompanyAdapter)getListAdapter()).getItem(position);
        Toast.makeText(getActivity(),company.getCompanyName(), Toast.LENGTH_SHORT).show();
    }

    private class CompanyAdapter extends ArrayAdapter<Company> {
        public CompanyAdapter(List<Company> companyList) {
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
            Company company = getItem(position);
            holder.companyTitleTextView.setText(company.getCompanyName());
            holder.companySalaryTextView.setText("Salary: " + String.valueOf(company.getSalary()));
            return convertView;
        }

        private class ViewHolder {
            TextView companyTitleTextView;
            TextView companySalaryTextView;
        }
    }
}
