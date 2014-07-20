package edu.truman.android.hackmidwest.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MajorsBank implements Serializable {

    private List<String> majors;

    public List<String> getMajors() {
        return majors;
    }

    public void setMajors(List<String> majors) {
        this.majors = majors;
    }

    @Override
    public String toString() {
        return "MajorsBank{" +
                "majors=" + majors +
                '}';
    }
}
