package com.ravisravan.infyassignment.viewmodels;

import android.databinding.BaseObservable;

import com.ravisravan.infyassignment.models.Row;

/**
 * Created by ravisravankumar on 13/07/18.
 */

public class FactItemViewModel extends BaseObservable {

    public Row row;

    public FactItemViewModel(Row row) {
        this.row = row;
    }

    public void setRow(Row row){
        this.row = row;
        notifyChange();
    }
}
