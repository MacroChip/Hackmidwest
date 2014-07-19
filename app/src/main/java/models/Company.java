package models;

import java.util.UUID;

public class Company {

    private UUID mId;
    private String companyName;
    private int salary;


    public Company(String companyName, int salary)  {
        mId = UUID.randomUUID();
        this.companyName = companyName;
        this.salary = salary;
    }

    public UUID getmId() {
        return mId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
