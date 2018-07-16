package com.ravisravan.infyassignment.network;

import com.ravisravan.infyassignment.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ravisravankumar on 11/07/18.
 */

public class APIServiceClient {

    private static Retrofit mRetrofit;
    private static final Object LOCK = new Object();

    private APIServiceClient() {

    }

    public static Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            synchronized (LOCK) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create()).build();
                }
            }
        }
        return mRetrofit;
    }

}
