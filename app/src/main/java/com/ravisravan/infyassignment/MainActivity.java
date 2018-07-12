package com.ravisravan.infyassignment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ravisravan.infyassignment.databinding.ActivityMainBinding;
import com.ravisravan.infyassignment.models.FactsResponseModel;
import com.ravisravan.infyassignment.network.APIServiceClient;
import com.ravisravan.infyassignment.network.FactsService;
import com.ravisravan.infyassignment.viewmodels.FactsViewModel;

import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Observer {

    private static String TAG = "MainActivity";
    private ActivityMainBinding activityMainBinding;
    private FactsViewModel factsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(activityMainBinding.toolbar);
        setupRecyclerView();
        setupObserver(factsViewModel);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        factsViewModel = new FactsViewModel(this);
        activityMainBinding.setFactsViewModel(factsViewModel);
    }

    private void setupRecyclerView() {

    }

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof FactsViewModel) {
            activityMainBinding.setFactsViewModel((FactsViewModel)observable);
        }
    }
}
