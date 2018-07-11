package com.ravisravan.infyassignment.network;

import com.ravisravan.infyassignment.models.FactsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ravisravankumar on 11/07/18.
 */

public interface FactsService {

    @GET("s/2iodh4vg0eortkl/facts.js")
    Call<FactsResponseModel> getFacts();
}
