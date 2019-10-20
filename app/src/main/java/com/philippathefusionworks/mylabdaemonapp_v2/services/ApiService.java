package com.philippathefusionworks.mylabdaemonapp_v2.services;

import com.philippathefusionworks.mylabdaemonapp_v2.models.ActionList;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {

    @GET("actions")
    Call<ActionList> getActions();
}
