package com.ravisravan.infyassignment.viewmodels;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ravisravan.infyassignment.MainActivity;
import com.ravisravan.infyassignment.models.Row;
import com.ravisravan.infyassignment.network.APIServiceClient;
import com.ravisravan.infyassignment.network.FactsService;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by ravikumar on 7/18/18.
 */

@RunWith(AndroidJUnit4.class)
public class FactsViewModelTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFactsViewModelObjectCreation() {
        MainActivity mainActivity = activityTestRule.getActivity();
        FactsViewModel factsViewModel = mainActivity.getFactsViewModel();
        Assert.assertNotNull(factsViewModel);
    }

    @Test
    public void testServerData() {
        MainActivity mainActivity = activityTestRule.getActivity();
        FactsViewModel factsViewModel = mainActivity.getFactsViewModel();
        Assert.assertNotNull(factsViewModel.getFacts());
        factsViewModel.getFacts().observe(mainActivity, new Observer<List<Row>>() {
            @Override
            public void onChanged(@Nullable List<Row> rows) {
                Assert.assertNotNull(rows);
            }
        });
    }

    @Test
    public void testForSameInstance() {
        MainActivity mainActivity = activityTestRule.getActivity();
        FactsViewModel factsViewModel = mainActivity.getFactsViewModel();
        FactsViewModel factsViewModel1 = ViewModelProviders.of(mainActivity).get(FactsViewModel.class);
        Assert.assertSame(factsViewModel,factsViewModel1);
    }

    @Test
    public void testForRetrofitInstance() {
        Retrofit retrofit = APIServiceClient.getRetrofitInstance();
        Assert.assertNotNull(retrofit);
    }

    @Test
    public void testFactsService() {
        FactsService factsService = APIServiceClient.getRetrofitInstance().create(FactsService.class);
        Assert.assertNotNull(factsService);
    }
}