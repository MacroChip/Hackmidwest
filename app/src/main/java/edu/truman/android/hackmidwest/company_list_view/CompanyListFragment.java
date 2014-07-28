package edu.truman.android.hackmidwest.company_list_view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;

import java.util.List;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.main_screen_view.MajorFragment;
import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import edu.truman.android.hackmidwest.single_company_view.SingleCompanyActivity;
import edu.truman.android.hackmidwest.single_company_view.SingleCompanyViewFragment;
import roboguice.fragment.RoboListFragment;

public class CompanyListFragment extends RoboListFragment {
    public static final String LIST = "some list";
    @Inject
    ExperienceEntry experienceEntry;
    @Inject
    CompanyBank companyBank;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityIntent(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setHasOptionsMenu(true);
        CompanyAdapter adapter = new CompanyAdapter(MajorFragment.listToBeSent);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (NavUtils.getParentActivityName(getActivity()) != null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ExperienceEntry company = ((CompanyAdapter) getListAdapter()).getItem(position);
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
                holder.companyLogoImageView = (ImageView) convertView.findViewById(R.id.company_logo);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ExperienceEntry companyEntry = getItem(position);

            holder.companyTitleTextView.setText(companyEntry.getName());
            return convertView;
        }

        private class ViewHolder {
            TextView companyTitleTextView;
            ImageView companyLogoImageView;
        }
    }
}
