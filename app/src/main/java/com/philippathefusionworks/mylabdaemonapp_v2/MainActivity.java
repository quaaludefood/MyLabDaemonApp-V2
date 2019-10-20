package com.philippathefusionworks.mylabdaemonapp_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.philippathefusionworks.mylabdaemonapp_v2.models.Action;
import com.philippathefusionworks.mylabdaemonapp_v2.models.ActionList;
import com.philippathefusionworks.mylabdaemonapp_v2.services.ApiService;
import com.philippathefusionworks.mylabdaemonapp_v2.retrofit.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Action> actionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadActionsData();
    }

    protected void loadActionsData(){

       final ProgressDialog dialog;

        /**
         * Progress Dialog for User Interaction
         */
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Getting JSON data");
        dialog.setMessage("Please wait...");
        dialog.show();

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        Call<ActionList> call = api.getActions();

        call.enqueue(new Callback<ActionList>() {
            @Override
            public void onResponse(Call<ActionList> call, Response<ActionList> response) {
                dialog.dismiss();

                if(response.isSuccessful()) {

                    actionList = response.body().getActions();
                }
            }

            @Override
            public void onFailure(Call<ActionList> call, Throwable t) {

            }
        });

    }
}
