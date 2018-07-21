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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private FragmentMainBinding fragmentMainBinding;
    private FactsViewModel factsViewModel;

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
        assert ((MainActivity)getActivity()) != null;
        factsViewModel = ((MainActivity)getActivity()).getFactsViewModel();
        if (NetworkUtils.hasInternetConnection(getActivity())) {
            factsViewModel.getFactsList();
        } else {
            factsViewModel.showConnectivityError();
        }
        fragmentMainBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((FactsAdapter) fragmentMainBinding.factsRecyclerView.getAdapter()).clear();
                if (NetworkUtils.hasInternetConnection(getActivity())) {
                    factsViewModel.getFactsList();
                } else {
                    fragmentMainBinding.swipeRefreshLayout.setRefreshing(false);
                    factsViewModel.showConnectivityError();
                }
                fragmentMainBinding.setFactsViewModel(factsViewModel);
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
