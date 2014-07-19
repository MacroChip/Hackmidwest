package edu.truman.android.hackmidwest.main_screen_view;

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

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.company_list_view.CompanyListActivity;
import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.CompanyBank;

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

    private class GlassDoorTask extends AsyncTask<Void, Void, String> {


        String responseString = null;

        @Override
        protected String doInBackground(Void... voids) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse;
            URL url = null;
            try {
                //&useragent=Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36
                url = new URL("http://api.glassdoor.com/api/api.htm?v=1&format=json&t.p=21077&t.k=hZhrmrE66kM&userip=198.248.61.62&action=employers&q=asynchrony&ps=1");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream in = connection.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }
                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                responseString = out.toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String string) {
//            Log.d("ASYnc", string);
            super.onPostExecute(string);
        }
    }
}
