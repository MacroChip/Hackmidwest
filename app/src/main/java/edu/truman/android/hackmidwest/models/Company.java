package edu.truman.android.hackmidwest.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements Serializable {

    private int id;
    private String name;
    private String website;
    //isEEP
    //exactMatch
    //industry
    private int numberOfRatings;
    private String squareLogo;
    private int overallRating;
    private String ratingDescription;
    private String cultureAndValuesRating;
    private String seniorLeadershipRating;
    private String compensationAndBenefitsRating;
    private String careerOpportunitiesRating;
    //featuredReview
    private Ceo ceo = new Ceo();

    private int salary;


    public Company(String companyName, int salary)  {
        name = companyName;
        this.salary = salary;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getSquareLogo() {
        return squareLogo;
    }

    public void setSquareLogo(String squareLogo) {
        this.squareLogo = squareLogo;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public String getRatingDescription() {
        return ratingDescription;
    }

    public void setRatingDescription(String ratingDescription) {
        this.ratingDescription = ratingDescription;
    }

    public String getCultureAndValuesRating() {
        return cultureAndValuesRating;
    }

    public void setCultureAndValuesRating(String cultureAndValuesRating) {
        this.cultureAndValuesRating = cultureAndValuesRating;
    }

    public String getSeniorLeadershipRating() {
        return seniorLeadershipRating;
    }

    public void setSeniorLeadershipRating(String seniorLeadershipRating) {
        this.seniorLeadershipRating = seniorLeadershipRating;
    }

    public String getCompensationAndBenefitsRating() {
        return compensationAndBenefitsRating;
    }

    public void setCompensationAndBenefitsRating(String compensationAndBenefitsRating) {
        this.compensationAndBenefitsRating = compensationAndBenefitsRating;
    }

    public String getCareerOpportunitiesRating() {
        return careerOpportunitiesRating;
    }

    public void setCareerOpportunitiesRating(String careerOpportunitiesRating) {
        this.careerOpportunitiesRating = careerOpportunitiesRating;
    }

    public Ceo getCeo() {
        return ceo;
    }

    public void setCeo(Ceo ceo) {
        this.ceo = ceo;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", numberOfRatings=" + numberOfRatings +
                ", squareLogo='" + squareLogo + '\'' +
                ", overallRating=" + overallRating +
                ", ratingDescription='" + ratingDescription + '\'' +
                ", cultureAndValuesRating='" + cultureAndValuesRating + '\'' +
                ", seniorLeadershipRating='" + seniorLeadershipRating + '\'' +
                ", compensationAndBenefitsRating='" + compensationAndBenefitsRating + '\'' +
                ", careerOpportunitiesRating='" + careerOpportunitiesRating + '\'' +
                ", ceo=" + ceo.toString() +
                ", salary=" + salary +
                '}';
    }
}
