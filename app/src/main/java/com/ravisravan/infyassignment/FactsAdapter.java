package com.ravisravan.infyassignment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ravisravan.infyassignment.databinding.FactItemBinding;
import com.ravisravan.infyassignment.models.Row;
import com.ravisravan.infyassignment.viewmodels.FactItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by ravisravankumar on 13/07/18.
 */

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactsAdapterViewHolder> {

    private List<Row> rowList;

    public FactsAdapter() {
        this.rowList = Collections.emptyList();
    }

    @Override
    public FactsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FactItemBinding factItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.fact_item, parent
                , false);
        return new FactsAdapterViewHolder(factItemBinding);
    }

    @Override
    public void onBindViewHolder(FactsAdapterViewHolder holder, int position) {
        holder.bindFact(rowList.get(position));
    }

    @Override
    public int getItemCount() {
        return rowList.size();
    }

    public void setRowList(List<Row> rowList) {
        this.rowList = rowList;
        notifyDataSetChanged();
    }

    public void clear() {
        rowList.clear();
        notifyDataSetChanged();
    }

    public static class FactsAdapterViewHolder extends RecyclerView.ViewHolder {

        FactItemBinding factItemBinding;

        public FactsAdapterViewHolder(FactItemBinding factItemBinding) {
            super(factItemBinding.container);
            this.factItemBinding = factItemBinding;
        }

        void bindFact(Row row) {
            if (factItemBinding.getFactItemViewModel() == null) {
                factItemBinding.setFactItemViewModel(new FactItemViewModel(row));
            } else {
                factItemBinding.getFactItemViewModel().setRow(row);
            }
        }
    }
}
