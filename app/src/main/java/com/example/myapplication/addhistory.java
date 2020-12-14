package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addhistory extends AppCompatActivity {
    private TextView editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addhistory);

        editTextName = findViewById(R.id.editTextName);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sName = editTextName.getText().toString().trim();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String playerDataToPass = bundle.getString("stringToSend").trim();

        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }
        else if(sName.equals("Name?")){
            editTextName.setError("Invalid Name");
            editTextName.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating history log
                tableData data = new tableData();
                data.setName(sName);
                data.setPlayerData(playerDataToPass);

                //adding to database
                databaseClient.getInstance(getApplicationContext()).getAppDatabase().userDao().insert(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}
