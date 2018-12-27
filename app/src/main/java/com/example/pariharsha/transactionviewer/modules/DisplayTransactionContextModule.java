package com.example.pariharsha.transactionviewer.modules;

import android.content.Context;

import com.example.pariharsha.transactionviewer.DisplayTransactionActivity;
import com.example.pariharsha.transactionviewer.qualifier.ActivityContext;
import com.example.pariharsha.transactionviewer.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DisplayTransactionContextModule {

    public Context context;
    private DisplayTransactionActivity displayTransactionActivity;

    public DisplayTransactionContextModule(DisplayTransactionActivity displayTransactionActivity){
        this.displayTransactionActivity = displayTransactionActivity;
        context = displayTransactionActivity;
    }

    @Provides
    @ActivityScope
    public DisplayTransactionActivity providesDisplayTransactionActivity(){
        return displayTransactionActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}
