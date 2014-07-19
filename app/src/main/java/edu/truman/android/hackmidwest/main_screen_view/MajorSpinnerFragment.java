package edu.truman.android.hackmidwest.main_screen_view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.company_list_view.CompanyListActivity;
import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.CompanyBank;
import roboguice.fragment.RoboFragment;
import roboguice.util.RoboAsyncTask;

public class MajorSpinnerFragment extends RoboFragment {

    @Inject
    CompanyBank companyBank;
    private Button submitCompanyButton;
    private Spinner spinner;
    ArrayAdapter adapter;

    List<String> majors = new ArrayList<String>();
    Map<String, List<Company>> majorToCompanyMap = new HashMap<String, List<Company>>();


    //    String[] testCompanies = new String[] {"CERNER", "BOEING", "ASYNCHRONY"};
    private String companyToSearch = null;

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
                Intent i = new Intent(getActivity(), CompanyListActivity.class);
                startActivity(i);
            }
        });
        setCompanyList();
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

    private void setCompanyList() {
        new GlassDoorTask(companyToSearch).execute();
    }

    private class GlassDoorTask extends RoboAsyncTask<String> {

        @Inject
        private CompanyBank companyBank;

        private final String company;
        String responseString = null;

        public GlassDoorTask(String company) {
            super(getActivity()); //TODO: does this matter?
            this.company = company;
        }

        public String call() throws Exception {
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
                Log.d("MajorSpinnerFragment", responseString);
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
        protected void onSuccess(String response) throws Exception {
            super.onSuccess(response);
            if (companyBank.getCompanyList() == null) {
                companyBank.setCompanyList(new ArrayList<Company>());
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            Company company = mapper.readValue(jsonNode.get("response").get("employers").get(0), Company.class);
            companyBank.getCompanyList().add(company);
        }
    }
}
