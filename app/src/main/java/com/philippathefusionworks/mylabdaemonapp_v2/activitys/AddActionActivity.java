package com.philippathefusionworks.mylabdaemonapp_v2.activitys;

import androidx.appcompat.app.AppCompatActivity;
import com.philippathefusionworks.mylabdaemonapp_v2.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.UUID;

//When the button in MainActivity screen gets clicked, the OnView method is parsed this AddActionActivity class
public class AddActionActivity extends AppCompatActivity {

    public static final String EXTRA_IDENTIFIER =
            "com.philippathefusionworks.mylabdaemonapp_v2.EXTRA_IDENTIFIER";

    public static final String EXTRA_NAME =
            "com.philippathefusionworks.mylabdaemonapp_v2.EXTRA_NAME";


    public static final String EXTRA_ACTIVE =
            "com.philippathefusionworks.mylabdaemonapp_v2.EXTRA_ACTIVE";



    private EditText editTextName;
    private RadioButton editActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);


        editTextName = findViewById(R.id.edit_text_name);
        editActive = findViewById(R.id.radio_active);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_IDENTIFIER))
        {
            setTitle("Edit Action");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
           // editActive.setChecked(parseBoolean(intent.getStringExtra(EXTRA_ACTIVE));
            Boolean xxx = Boolean.parseBoolean(intent.getStringExtra(EXTRA_ACTIVE));
            editActive.setChecked(xxx);
        }
        else
        {
            setTitle("Add Action!");
        }

    }

    private void saveAction(){
        String name = editTextName.getText().toString();
        boolean active = editActive.isChecked();

        if(name.trim().isEmpty())
        {
            toastMessage("Please insert a name!");
            return;
        }
//Using intents allows this to be strarted from the main activity
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_ACTIVE, active);

        int identifier = getIntent().getIntExtra(EXTRA_IDENTIFIER, -1);

        if(identifier != -1)
        {
            data.putExtra(EXTRA_IDENTIFIER, identifier);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    //Tell the system to use our add action menu as the menu of this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                saveAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(AddActionActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
