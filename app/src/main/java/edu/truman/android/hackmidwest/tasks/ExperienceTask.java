package edu.truman.android.hackmidwest.tasks;

import android.content.Context;
import android.util.Log;

import com.google.inject.Inject;
import com.squareup.otto.Bus;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import edu.truman.android.hackmidwest.events.ExperienceTaskCompleteEvent;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.ExperienceEntry;

public class ExperienceTask extends UrlTask {

    public static final String EXPERIENCE = "http://hackmw-wruman.rhcloud.com/api/employer-list";
    @Inject
    private ExperienceBank experienceBank;
    @Inject
    private Bus bus;

    public ExperienceTask(Context context) {
        super(context, EXPERIENCE); //TODO: parameterize this for different schools
    }

    @Override
    protected void onSuccess(String response) throws Exception {
        super.onSuccess(response);
        if (experienceBank.getCompanies() == null) {
            experienceBank.setCompanies(new ArrayList<ExperienceEntry>());
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response);
        List<ExperienceEntry> companies = new ArrayList<ExperienceEntry>();
        for (int i = 0; i < jsonNode.get("out").size(); i++) {
            companies.add(mapper.readValue(jsonNode.get("out").get(i), ExperienceEntry.class));
        }
        experienceBank.setCompanies(companies);
        Log.d("Experience", experienceBank.toString());
        bus.post(new ExperienceTaskCompleteEvent());
    }
}
