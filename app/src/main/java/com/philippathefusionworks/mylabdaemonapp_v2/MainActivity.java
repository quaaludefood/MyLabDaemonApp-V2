package com.philippathefusionworks.mylabdaemonapp_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.widget.Toast;
import android.os.Bundle;

import com.philippathefusionworks.mylabdaemonapp_v2.models.Action;
import com.philippathefusionworks.mylabdaemonapp_v2.models.ActionList;
import com.philippathefusionworks.mylabdaemonapp_v2.services.ApiService;
import com.philippathefusionworks.mylabdaemonapp_v2.retrofit.RetroClient;
import com.philippathefusionworks.mylabdaemonapp_v2.utils.InternetConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private ArrayList<Action> actionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        loadActionsData();
    }

    protected void loadActionsData(){

        if (InternetConnection.checkConnection(getApplicationContext())) {
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

                    textViewResult.setText("code: " + response.code());
                    if(response.isSuccessful()) {

                        actionList = response.body().getActions();
;

                            textViewResult.append(actionList.toString());

                    }
                }

                @Override
                public void onFailure(Call<ActionList> call, Throwable t) {
                   // toastMessage("Failure: " + t.getMessage());
                    dialog.setMessage("Failure: " + t.getMessage());
                }
            });
        } else {
            toastMessage("Check your internet connection!");
        }



    }

    private void toastMessage(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
