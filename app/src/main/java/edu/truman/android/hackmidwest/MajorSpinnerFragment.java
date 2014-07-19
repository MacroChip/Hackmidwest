package edu.truman.android.hackmidwest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Company;
import models.CompanyBank;

public class MajorSpinnerFragment extends Fragment {

    CompanyBank companyBank;

    private Spinner spinner;
    private Button submitCompanyButton;
    String[] testCompanies = new String[] {"CERNER", "BOEING", "ASYNCHRONY"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        companyBank = CompanyBank.getInstance(getActivity());
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
        new GlassDoorTask().execute(null, null, null);
        return view;
    }

    private void setCompanyList() {
        List<Company> companyList = new ArrayList<Company>();
        for (int i = 0; i < 60; i++) {
            companyList.add(new Company(testCompanies[new Random().nextInt(3)], i));
        }
        companyBank.setCompanyList(companyList);
    }

    private class GlassDoorTask extends AsyncTask<Void, Void, Void> {


        String responseString = null;

        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse;
            try {
                //http://api.glassdoor.com/api/api.htm?v=1&format=json&t.p=21077&t.k=hZhrmrE66kM&userip=198.248.61.62&useragent=Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36&action=employers&q=asynchrony&ps=1
                String uri = "www.google.com";
                HttpGet httpGet = new HttpGet(uri);
                httpResponse = httpClient.execute(httpGet);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
                if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    httpResponse.getEntity().getContent().close();
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("ASYnc", responseString);
        }
    }
}
