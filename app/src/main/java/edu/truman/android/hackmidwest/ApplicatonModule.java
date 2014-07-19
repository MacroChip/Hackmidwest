package edu.truman.android.hackmidwest;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import models.CompanyBank;

public class ApplicatonModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CompanyBank.class).in(Singleton.class);
    }
}
