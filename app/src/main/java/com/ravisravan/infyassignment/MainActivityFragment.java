package com.ravisravan.infyassignment;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ravisravan.infyassignment.databinding.FragmentMainBinding;
import com.ravisravan.infyassignment.models.Row;
import com.ravisravan.infyassignment.viewmodels.FactsViewModel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;
    FactsViewModel factsViewModel;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initDataBinding(inflater, container);
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        factsViewModel = ((MainActivity)getActivity()).getFactsViewModel();
        fragmentMainBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((FactsAdapter)fragmentMainBinding.factsRecyclerView.getAdapter()).clear();
                factsViewModel.getFactsList();
            }
        });

        factsViewModel.getFacts().observe(this, new android.arch.lifecycle.Observer<List<Row>>() {
            @Override
            public void onChanged(@Nullable List<Row> rows) {
                fragmentMainBinding.setFactsViewModel(factsViewModel);
                if (fragmentMainBinding.swipeRefreshLayout.isRefreshing()) {
                    fragmentMainBinding.swipeRefreshLayout.setRefreshing(false);
                }
                FactsAdapter factsAdapter = (FactsAdapter) fragmentMainBinding.factsRecyclerView.getAdapter();
                FactsViewModel factsViewModel = ((MainActivity)getActivity()).getFactsViewModel();
                factsAdapter.setRowList(factsViewModel.getRowList());
            }
        });
        initRecyclerView();
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container) {
        fragmentMainBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
    }

    private void initRecyclerView() {
        FactsAdapter factsAdapter = new FactsAdapter();
        fragmentMainBinding.factsRecyclerView.setAdapter(factsAdapter);
        fragmentMainBinding.factsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
