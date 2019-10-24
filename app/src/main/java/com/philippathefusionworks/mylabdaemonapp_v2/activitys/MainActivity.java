package com.philippathefusionworks.mylabdaemonapp_v2.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.philippathefusionworks.mylabdaemonapp_v2.R;
import com.philippathefusionworks.mylabdaemonapp_v2.adaptors.ActionAdaptor;
import com.philippathefusionworks.mylabdaemonapp_v2.models.Action;
import com.philippathefusionworks.mylabdaemonapp_v2.viewModels.ActionViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Retrofit Testing
    private TextView textViewResult;
    private ArrayList<Action> actionList;
//End RetroTesting

    private ActionViewModel actionViewModel;
    public static final int ADD_ACTION_REQUEST = 1;
    public static final int EDIT_ACTION_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddAction = findViewById(R.id.button_add_action);
        buttonAddAction.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddActionActivity.class);
        startActivityForResult(intent, ADD_ACTION_REQUEST);
    }
});


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ActionAdaptor adaptor = new ActionAdaptor();
        recyclerView.setAdapter(adaptor);


        actionViewModel = ViewModelProviders.of(this).get(ActionViewModel.class);
        actionViewModel.getAllActions().observe(this, new Observer<List<com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action>>() {
            @Override
            public void onChanged(@Nullable List<com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action> actions) {
                adaptor.submitList(actions);
            }
        });
    //Parse it the values of what type of touch we listen for
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            //drag and drop, not used in this example
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    actionViewModel.delete(adaptor.getActionAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adaptor.setOnItemClickListener(new ActionAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action action) {
                Intent intent = new Intent(MainActivity.this, AddActionActivity.class);
                intent.putExtra(AddActionActivity.EXTRA_IDENTIFIER, action.getIdentifier());
                intent.putExtra(AddActionActivity.EXTRA_NAME, action.getName());
                intent.putExtra(AddActionActivity.EXTRA_ACTIVE, action.getActive());
                startActivityForResult(intent, EDIT_ACTION_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //Save the action to the databe, then dispaly it in the list of action recyclerview
        //Check with else if for whether we're inserting or editting
        if (requestCode == ADD_ACTION_REQUEST && resultCode == RESULT_OK) {

            String name = data.getStringExtra(AddActionActivity.EXTRA_NAME);
            boolean active = data.getBooleanExtra(AddActionActivity.EXTRA_ACTIVE, true);

            com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action action =
            new com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action(active, name);
           actionViewModel.insert(action);

            Toast.makeText(this, "Action saved :)", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_ACTION_REQUEST&& resultCode == RESULT_OK) {
            int identifier = data.getIntExtra(AddActionActivity.EXTRA_IDENTIFIER, -1);
            if(identifier == -1)
            {
                Toast.makeText(this, "Action can't be updated :0", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddActionActivity.EXTRA_NAME);
            boolean active = data.getBooleanExtra(AddActionActivity.EXTRA_ACTIVE, true);
            com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action action = new com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action(active, name);
           action.setIdentifier(identifier);
           actionViewModel.update(action);

            Toast.makeText(this, "Action updated :)", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Action not saved :0", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_actions:
                actionViewModel.deleteAllActions();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**/ protected void loadActionsData(){
        //See GoogleDive
    }


private void toastMessage(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
