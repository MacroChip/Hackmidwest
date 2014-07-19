package edu.truman.android.hackmidwest.models;

import android.content.Context;

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

//    public static CompanyBank getInstance(Context context){
//        if (companyBank == null) {
//            companyBank = new CompanyBank(context.getApplicationContext());
//        }
//        return companyBank;
//    }
}
