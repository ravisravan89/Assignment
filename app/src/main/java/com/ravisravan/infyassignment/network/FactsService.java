package com.ravisravan.infyassignment.network;

import com.ravisravan.infyassignment.models.FactsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * An interface required to call the api
 */

public interface FactsService {

    @GET("s/2iodh4vg0eortkl/facts.js")
    Call<FactsResponseModel> getFacts();
}
