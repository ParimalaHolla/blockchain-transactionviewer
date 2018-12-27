package com.example.pariharsha.transactionviewer.appcomponent;


import android.content.Context;

import com.example.pariharsha.transactionviewer.MyApplication;
import com.example.pariharsha.transactionviewer.modules.ContextModule;
import com.example.pariharsha.transactionviewer.modules.RetrofitModule;
import com.example.pariharsha.transactionviewer.qualifier.ApplicationContext;
import com.example.pariharsha.transactionviewer.retrofit.APIInterface;
import com.example.pariharsha.transactionviewer.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class,RetrofitModule.class})
public interface ApplicationComponent {

    APIInterface getApiInterface();

    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication myApplication);
}
