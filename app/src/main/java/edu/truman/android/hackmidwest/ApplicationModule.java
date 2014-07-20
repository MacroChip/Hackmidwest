package edu.truman.android.hackmidwest;

import android.content.Context;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import edu.truman.android.hackmidwest.models.CompanyBank;
import edu.truman.android.hackmidwest.models.ExperienceBank;
import edu.truman.android.hackmidwest.models.MajorsBank;

public class ApplicationModule extends AbstractModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Override
    protected void configure() {
        bind(CompanyBank.class).in(Singleton.class);
        bind(ExperienceBank.class).in(Singleton.class);
        bind(MajorsBank.class).in(Singleton.class);
    }
}
