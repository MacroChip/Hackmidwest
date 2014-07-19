package edu.truman.android.hackmidwest.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by chip on 7/19/14.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ceo implements Serializable{

    private String name;
    private String title;
    //image
    private int numberOfRatings;
    private int pctApprove;
    private int pctDisapprove;

    public Ceo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public int getPctApprove() {
        return pctApprove;
    }

    public void setPctApprove(int pctApprove) {
        this.pctApprove = pctApprove;
    }

    public int getPctDisapprove() {
        return pctDisapprove;
    }

    public void setPctDisapprove(int pctDisapprove) {
        this.pctDisapprove = pctDisapprove;
    }
}
