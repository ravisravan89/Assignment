package com.ravisravan.infyassignment.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableInt;
import android.view.View;

import com.ravisravan.infyassignment.models.FactsResponseModel;
import com.ravisravan.infyassignment.models.Row;
import com.ravisravan.infyassignment.network.APIServiceClient;
import com.ravisravan.infyassignment.network.FactsService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ravisravankumar on 12/07/18.
 */

public class FactsViewModel extends ViewModel {

    private static final String TAG = "FactsViewModel";
    public String title;
    private List<Row> rowList;
    private MutableLiveData<List<Row>> rowListLiveData;
    public ObservableInt progressBar;
    public ObservableInt messageLable;
    public ObservableInt recyclerView;
    public boolean isServerError = false;

    public FactsViewModel(){
        this.rowList = new ArrayList<>();
        rowListLiveData = new MutableLiveData<>();
        rowListLiveData.setValue(rowList);
        progressBar = new ObservableInt(View.VISIBLE);
        messageLable = new ObservableInt(View.GONE);
        recyclerView = new ObservableInt(View.GONE);
    }

    public void getFactsList() {

        FactsService factsService = APIServiceClient.getRetrofitInstance().create(FactsService.class);
        Disposable disposable = factsService.getFacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FactsResponseModel>() {
                    @Override
                    public void accept(FactsResponseModel response) throws Exception {
                        if (response != null) {
                            isServerError = false;
                            progressBar = new ObservableInt(View.GONE);
                            messageLable = new ObservableInt(View.GONE);
                            recyclerView = new ObservableInt(View.VISIBLE);
                            title = response.getTitle();
                            updateRowList(response.getRows());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isServerError = true;
                        progressBar = new ObservableInt(View.GONE);
                        messageLable = new ObservableInt(View.VISIBLE);
                        recyclerView = new ObservableInt(View.GONE);
                    }
                });
    }


    private void updateRowList(List<Row> rowList) {
        this.rowList = rowList;
        rowListLiveData.setValue(rowList);
    }

    public void showConnectivityError() {
        isServerError = false;
        progressBar = new ObservableInt(View.GONE);
        messageLable = new ObservableInt(View.VISIBLE);
        recyclerView = new ObservableInt(View.GONE);
    }

    public List<Row> getRowList() {
        return rowList;
    }

    public LiveData<List<Row>> getFacts() {
        return this.rowListLiveData;
    }
}
