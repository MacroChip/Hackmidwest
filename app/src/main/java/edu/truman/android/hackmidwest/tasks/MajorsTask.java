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

import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.MajorsBank;
import roboguice.util.RoboAsyncTask;

public class MajorsTask extends RoboAsyncTask<String> {

    public static final String MAJORS = "http://hackmw-wruman.rhcloud.com/api/major-list";

    @Inject
    private MajorsBank majorsBank;

    String responseString = null;

    public MajorsTask(Context context) {
        super(context); //TODO: does this matter?
    }

    public String call() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse;
        URL url = null;
        try {
            url = new URL(MAJORS);
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
        if (majorsBank.getMajors() == null) {
            majorsBank.setMajors(new ArrayList<String>());
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        List<String> majors = mapper.readValue(jsonNode.get("majors"), List.class);
        majorsBank.setMajors(majors);
    }
}

