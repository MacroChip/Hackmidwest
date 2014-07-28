package edu.truman.android.hackmidwest.tasks;

import android.content.Context;

import com.google.inject.Inject;
import com.squareup.otto.Bus;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import edu.truman.android.hackmidwest.events.GlassdoorTaskCompleteEvent;
import edu.truman.android.hackmidwest.models.Company;
import edu.truman.android.hackmidwest.models.CompanyBank;

public class GlassdoorTask extends UrlTask {


    @Inject
    private CompanyBank companyBank;

    @Inject
    private Bus bus;

    String responseString = null;
    public GlassdoorTask(Context context, String glassdoorUrl) {
        super(context, glassdoorUrl); //trust url for now. More params todo
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
        List<Company> companies = new ArrayList<Company>();
        companies.add(company);
        companyBank.setCompanyList(companies);
        bus.post(new GlassdoorTaskCompleteEvent());
    }
}
