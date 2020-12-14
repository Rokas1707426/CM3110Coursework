package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class history extends AppCompatActivity {
    private RecyclerView recyclerView;
    //private TextView editTextName, editPlayerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        final Button bin = (Button) findViewById(R.id.binButton);
        // set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();

        /*// delete button listener
        final tableData data = (tableData) getIntent().getSerializableExtra("data");
        loadData(data);
        bin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(history.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData(data);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });*/
    }
    private void getData(){
        class GetData extends AsyncTask<Void, Void, List<tableData>> {

            @Override
            protected List<tableData> doInBackground(Void... voids) {
                List<tableData> dataList = databaseClient.getInstance(getApplicationContext()).getAppDatabase().userDao().getAll();
                return dataList;
            }

            @Override
            protected void onPostExecute(List<tableData> givenData) {
                super.onPostExecute(givenData);
                dataRecyclerViewAdapter adapter = new dataRecyclerViewAdapter(history.this, givenData);
                recyclerView.setAdapter(adapter);
            }
        }

        GetData gt = new GetData();
        gt.execute();
    }

    /*@NonNull
    private void loadData(tableData data) {
        editTextName.setText(data.getName());
        editPlayerData.setText(data.getPlayerData());
    }

    private void deleteData(final tableData data) {
        class DeleteData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                databaseClient.getInstance(getApplicationContext()).getAppDatabase().userDao().delete(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(history.this, MainActivity.class));
            }
        }

        DeleteData dt = new DeleteData();
        dt.execute();

    }*/
}
