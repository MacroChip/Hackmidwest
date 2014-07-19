package models;

import android.content.Context;

import com.google.inject.Singleton;

import java.lang.annotation.Annotation;
import java.util.List;

public class CompanyBank {

    private static CompanyBank companyBank;
    private Context context;


    private List<Company> companyList;

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    private CompanyBank(Context context) {
        this.context = context;
    }

    public static CompanyBank getInstance(Context context){
        if (companyBank == null) {
            companyBank = new CompanyBank(context.getApplicationContext());
        }
        return companyBank;
    }
}
