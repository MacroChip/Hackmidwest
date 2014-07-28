package edu.truman.android.hackmidwest.tasks;

import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.squareup.otto.Bus;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import edu.truman.android.hackmidwest.events.MajorsTaskCompleteEvent;
import edu.truman.android.hackmidwest.models.MajorsBank;

public class MajorsTask extends UrlTask {

    public static final String MAJORS = "http://hackmw-wruman.rhcloud.com/api/major-list";

    @Inject
    private MajorsBank majorsBank;

    @Inject
    private Bus bus;

    public MajorsTask(Context context) {
        super(context, MAJORS); //TODO: parameterize this for different schools
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
        Log.d("Experience", majorsBank.toString());
        bus.post(new MajorsTaskCompleteEvent());
    }
}

