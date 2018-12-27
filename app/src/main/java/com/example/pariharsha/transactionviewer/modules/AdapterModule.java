package com.example.pariharsha.transactionviewer.modules;

import com.example.pariharsha.transactionviewer.DisplayTransactionActivity;
import com.example.pariharsha.transactionviewer.RecyclerViewAdapter;
import com.example.pariharsha.transactionviewer.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DisplayTransactionContextModule.class})

public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }
    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(DisplayTransactionActivity displayTransactionActivity) {
        return displayTransactionActivity;
    }
}
