package com.example.pariharsha.transactionviewer;

import android.app.Activity;
import android.app.Application;

import com.example.pariharsha.transactionviewer.appcomponent.ApplicationComponent;
import com.example.pariharsha.transactionviewer.appcomponent.DaggerApplicationComponent;
import com.example.pariharsha.transactionviewer.modules.ContextModule;

public class MyApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
