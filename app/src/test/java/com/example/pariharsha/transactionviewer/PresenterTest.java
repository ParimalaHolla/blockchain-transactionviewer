package com.example.pariharsha.transactionviewer;

import com.example.pariharsha.transactionviewer.model.Transaction;
import com.example.pariharsha.transactionviewer.mvp.DisplayTransactionActivityContract;
import com.example.pariharsha.transactionviewer.mvp.PresenterImpl;
import com.example.pariharsha.transactionviewer.retrofit.APIInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;


public class PresenterTest {

    @Mock
    private APIInterface apiInterface;

    @Mock
    private DisplayTransactionActivityContract.View view;

    @Before
    public void setUp() {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook(){
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown(){
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void fetchValidDataShouldUpdateView(){
        Transaction transaction = new Transaction();

        Mockito.when(apiInterface.getData(Mockito.anyString())).thenReturn(Observable.just(transaction));
        PresenterImpl presenter = new PresenterImpl(apiInterface, view);
        presenter.loadData();

        Mockito.verify(view, Mockito.atLeastOnce()).showProgress();
        Mockito.verify(view, Mockito.atLeastOnce()).showData(transaction);
        Mockito.verify(view, Mockito.atLeastOnce()).hideProgress();
        Mockito.verify(view, Mockito.atLeastOnce()).showComplete();
    }

    @Test
    public void fetchErrorShouldReturnErrorToView() {
        Exception exception = new Exception();

        Mockito.when(apiInterface.getData(Mockito.anyString())).thenReturn(Observable.<Transaction>error(exception));
        PresenterImpl presenter = new PresenterImpl(apiInterface, view);
        presenter.loadData();

        Mockito.verify(view, Mockito.atLeastOnce()).showProgress();
        Mockito.verify(view, Mockito.atLeastOnce()).showError(Mockito.anyString());
        Mockito.verify(view, Mockito.never()).showComplete();
    }

}