package edu.truman.android.hackmidwest.models;

import java.io.Serializable;
import java.util.UUID;

public class Company implements Serializable {

    private UUID id;
    private String name;
    private String website;
    //iseep
    //exactMatch
    //industry
    private int numberOfRatings;
    private String squareLogo;
    private int overallRating;
    private String ratingDescription;
    private String cultureAndValuesRating;
    private String seniorLeadershipRating;

    private int salary;


    public Company(String name, int salary)  {
        id = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
