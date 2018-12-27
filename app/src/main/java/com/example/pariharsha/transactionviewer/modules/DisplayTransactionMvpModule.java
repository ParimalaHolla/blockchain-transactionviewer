package com.example.pariharsha.transactionviewer.modules;

import com.example.pariharsha.transactionviewer.mvp.DisplayTransactionActivityContract;
import com.example.pariharsha.transactionviewer.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DisplayTransactionMvpModule {
    private final DisplayTransactionActivityContract.View mView;

    public DisplayTransactionMvpModule(DisplayTransactionActivityContract.View mView){
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    DisplayTransactionActivityContract.View provideView(){
        return mView;
    }

}
