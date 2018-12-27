package com.example.pariharsha.transactionviewer.appcomponent;

import android.content.Context;

import com.example.pariharsha.transactionviewer.DisplayTransactionActivity;
import com.example.pariharsha.transactionviewer.modules.AdapterModule;
import com.example.pariharsha.transactionviewer.modules.DisplayTransactionMvpModule;
import com.example.pariharsha.transactionviewer.qualifier.ActivityContext;
import com.example.pariharsha.transactionviewer.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {AdapterModule.class,DisplayTransactionMvpModule.class},dependencies = ApplicationComponent.class)

public interface DisplayTransactionComponent {

    @ActivityContext
    Context geContext();
    void injectDisplayTransactionActivity(DisplayTransactionActivity displayTransactionActivity);
}
