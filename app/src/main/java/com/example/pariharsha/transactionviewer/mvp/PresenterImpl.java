package com.example.pariharsha.transactionviewer.mvp;

import android.util.Log;

import com.example.pariharsha.transactionviewer.model.Transaction;
import com.example.pariharsha.transactionviewer.retrofit.APIInterface;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PresenterImpl implements DisplayTransactionActivityContract.Presenter {

    APIInterface apiInterface;
    DisplayTransactionActivityContract.View mView;

    @Inject
    public PresenterImpl(APIInterface apiInterface, DisplayTransactionActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }

    @Override
    public void loadData() {
        mView.showProgress();
        apiInterface.getData("xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Transaction>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.hideProgress();

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        Log.v("Error msg",e.toString());
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(Transaction data) {
                        mView.showData(data);
                    }
                });
    }
}
