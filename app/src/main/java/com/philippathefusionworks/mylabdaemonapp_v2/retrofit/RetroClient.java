package com.philippathefusionworks.mylabdaemonapp_v2.retrofit;

import com.philippathefusionworks.mylabdaemonapp_v2.services.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    /********
     * URLS
     *******/

    private String urlDEBUG = "http://jsonplaceholder.typicode.com/";
    private String urlDEBUGLOCAL = "http://192.168.0.6:44337/api/";
    // Work local = "http://192.168.1.165:52477/api/";
   // Home local = "http://192.168.0.6:44337/api/";
    private String urlLIVE = "http://mylabdaemon.thefusion.works/api/";


    private static final String ROOT_URL = "http://192.168.1.165:52477/api/";

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

}
