package com.ravisravan.infyassignment.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.ravisravan.infyassignment.models.FactsResponseModel;
import com.ravisravan.infyassignment.models.Row;
import com.ravisravan.infyassignment.network.APIServiceClient;
import com.ravisravan.infyassignment.network.FactsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ravisravankumar on 12/07/18.
 */

public class FactsViewModel extends Observable {

    private Context context;
    private static final String TAG = "FactsViewModel";
    public String title;
    private List<Row> rowList;

    public FactsViewModel(@NonNull Context context){
        this.context = context;
        this.rowList = new ArrayList<>();
        getFactsList();
    }

    private void getFactsList() {

        FactsService factsService = APIServiceClient.getRetrofitInstance().create(FactsService.class);
        Disposable disposable = factsService.getFacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FactsResponseModel>() {
                    @Override
                    public void accept(FactsResponseModel response) throws Exception {
                        title = response.getTitle();
                        updateRowList(response.getRows());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


    private void updateRowList(List<Row> rowList) {
        this.rowList = rowList;
        setChanged();
        notifyObservers();
    }

    public List<Row> getRowList() {
        return rowList;
    }
}
