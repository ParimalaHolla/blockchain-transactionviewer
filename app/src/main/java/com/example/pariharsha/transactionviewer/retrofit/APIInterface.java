package com.example.pariharsha.transactionviewer.retrofit;

import com.example.pariharsha.transactionviewer.model.Transaction;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface {


    @GET("multiaddr?")
    Observable<Transaction> getData(@Query("active") String active);


}
