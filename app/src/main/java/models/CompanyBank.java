package models;

import com.google.inject.Singleton;

import java.lang.annotation.Annotation;
import java.util.List;

public class CompanyBank {


    private List<Company> companyList;

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }
}
