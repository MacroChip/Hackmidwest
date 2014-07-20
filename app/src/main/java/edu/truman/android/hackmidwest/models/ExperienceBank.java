package edu.truman.android.hackmidwest.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chip on 7/19/14.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceBank implements Serializable{

    @JsonProperty("out")
    private List<ExperienceEntry> companies;

    public List<ExperienceEntry> getCompanies() {
        return companies;
    }

    public void setCompanies(List<ExperienceEntry> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "ExperienceBank{" +
                "companies=" + companies +
                '}';
    }
}
