package com.example.pariharsha.transactionviewer.modules;

import android.content.Context;

import com.example.pariharsha.transactionviewer.qualifier.ApplicationContext;
import com.example.pariharsha.transactionviewer.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module

public class ContextModule {
    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext

    public Context provideContext(){
        return context;
    }
}
