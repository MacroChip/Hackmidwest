package edu.truman.android.hackmidwest.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chip on 7/19/14.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceEntry implements Serializable {

    private String name;
    private List<String> majors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMajors() {
        return majors;
    }

    public void setMajors(List<String> majors) {
        this.majors = majors;
    }

    @Override
    public String toString() {
        return "ExperienceEntry{" +
                "name='" + name + '\'' +
                ", majors=" + majors +
                '}';
    }
}
