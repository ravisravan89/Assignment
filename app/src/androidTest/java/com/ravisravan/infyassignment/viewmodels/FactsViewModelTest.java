package com.ravisravan.infyassignment.viewmodels;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ravisravan.infyassignment.MainActivity;
import com.ravisravan.infyassignment.models.Row;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by ravikumar on 7/18/18.
 */

@RunWith(AndroidJUnit4.class)
public class FactsViewModelTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testMethod() {
        MainActivity mainActivity = activityTestRule.getActivity();
        FactsViewModel factsViewModel = mainActivity.getFactsViewModel();
        Assert.assertNotNull(factsViewModel);
        Assert.assertNotNull(factsViewModel.getFacts());
        factsViewModel.getFacts().observe(mainActivity, new Observer<List<Row>>() {
            @Override
            public void onChanged(@Nullable List<Row> rows) {
                Assert.assertNotNull(rows);
            }
        });
        FactsViewModel factsViewModel1 = ViewModelProviders.of(mainActivity).get(FactsViewModel.class);
        Assert.assertEquals(factsViewModel,factsViewModel1);
    }
}