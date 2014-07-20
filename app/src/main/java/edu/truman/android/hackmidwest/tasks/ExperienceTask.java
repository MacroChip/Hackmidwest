package edu.truman.android.hackmidwest.tasks;

import android.content.Context;
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
import java.util.List;

import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;
import roboguice.util.RoboAsyncTask;

public class ExperienceTask extends RoboAsyncTask<String> {

    public static final String EXPERIENCE = "http://hackmw-wruman.rhcloud.com/api/employer-list";
    @Inject
    private ExperienceBank experienceBank;
    private String responseString = null;
    private final String school;

    public ExperienceTask(Context context, String school) {
        super(context); //TODO: does this matter?
        this.school = school;
    }

    public String call() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse;
        URL url = null;
        try {
            //&useragent=Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36
            url = new URL(EXPERIENCE);
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
    protected void onSuccess(String response) throws Exception {
        super.onSuccess(response);
        if (experienceBank.getCompanies() == null) {
            experienceBank.setCompanies(new ArrayList<ExperienceEntry>());
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        List<ExperienceEntry> experienceEntries = mapper.readValue(jsonNode.get("out"), List.class);
        experienceBank.setCompanies(experienceEntries);
    }
}
