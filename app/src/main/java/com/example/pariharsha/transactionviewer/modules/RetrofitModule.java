package com.example.pariharsha.transactionviewer.modules;

import com.example.pariharsha.transactionviewer.retrofit.APIInterface;
import com.example.pariharsha.transactionviewer.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
public class RetrofitModule {

    @Provides
    @ApplicationScope
    APIInterface getAPIInterface(Retrofit retrofit){
        return retrofit.create(APIInterface.class);
    }

    @Provides
    @ApplicationScope

    Retrofit getRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder().baseUrl("https://blockchain.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope

    OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @ApplicationScope

    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
