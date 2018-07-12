package com.ravisravan.infyassignment.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ravisravan.infyassignment.models.FactsResponseModel;
import com.ravisravan.infyassignment.models.Row;
import com.ravisravan.infyassignment.network.APIServiceClient;
import com.ravisravan.infyassignment.network.FactsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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
        factsService.getFacts().enqueue(new Callback<FactsResponseModel>() {
            @Override
            public void onResponse(Call<FactsResponseModel> call, Response<FactsResponseModel> response) {
                title = response.body().getTitle();
                updateRowList(response.body().getRows());
            }

            @Override
            public void onFailure(Call<FactsResponseModel> call, Throwable t) {
                Log.d(TAG, "Failure : "+t.getLocalizedMessage());
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
