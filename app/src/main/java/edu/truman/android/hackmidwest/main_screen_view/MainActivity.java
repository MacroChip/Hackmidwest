package edu.truman.android.hackmidwest.main_screen_view;

import android.os.Bundle;
import android.util.Log;

import com.google.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.truman.android.hackmidwest.R;
import edu.truman.android.hackmidwest.SingleFragmentActivity;
import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.CompanyBank;
import roboguice.fragment.RoboFragment;
import roboguice.util.RoboAsyncTask;


public class MainActivity extends SingleFragmentActivity {
    @Inject
    CompanyBank companyBank;
    private String companyToSearch = null;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setCompanyList();
    }
    @Override
    protected RoboFragment createFragment() {
        return new MajorSpinnerFragment();
    }

    @Override
    protected int getContainerResource() {
        return R.id.fragmentContainer;
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
            super(MainActivity.this); //TODO: does this matter?
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
