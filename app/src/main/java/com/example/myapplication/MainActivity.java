package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
            // When user reopens the app
            // after applying dark/light mode
            if (isDarkModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            final TextView player1TextField = (TextView)findViewById(R.id.player1TextField);
            player1TextField.setText("" + 20);
            final TextView player2TextField = (TextView)findViewById(R.id.player2TextField);
            player2TextField.setText("" + 20);
            /*final TextView player3TextField = (TextView)findViewById(R.id.player3TextField);
            player1TextField.setText(" " + 20);
            final TextView player4TextField = (TextView)findViewById(R.id.player4TextField);
            player2TextField.setText(" " + 20);*/

            final Button player1Plius = findViewById(R.id.player1Plius);
            final Button player1Minus = findViewById(R.id.player1Minus);
            final Button player2Plius = findViewById(R.id.player2Plius);
            final Button player2Minus = findViewById(R.id.player2Minus);
            /*final Button player3Plius = findViewById(R.id.player3Plius);
            final Button player3Minus = findViewById(R.id.player3Minus);
            final Button player4Plius = findViewById(R.id.player4Plius);
            final Button player4Minus = findViewById(R.id.player4Minus);*/
            final Button reset = findViewById(R.id.reset);
            final Button map = findViewById(R.id.map);
            final Button history = findViewById(R.id.history);
            final Button settings = findViewById(R.id.settings);
            final Button record = findViewById(R.id.record);
            final int[] player1 = new int[500]; //keeps track of how many times player 1 hit points changed
            final int[] player1HowManyTimes = new int[] {0};
            final int[] player2 = new int[500]; //keeps track of how many times player 2 hit points changed
            final int[] player2HowManyTimes = new int[] {0};




            player1Plius.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    int presentValueOfString = Integer.parseInt(String.valueOf(player1TextField.getText()));
                    presentValueOfString++;
                    player1TextField.setText("" + presentValueOfString);
                    player1HowManyTimes[0]++;
                    player1[player1HowManyTimes[0]] = presentValueOfString;
                }
            });
            player1Minus.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    int presentValueOfString = Integer.parseInt(String.valueOf(player1TextField.getText()));
                    //int presentValueOfString = 0;
                    presentValueOfString--;
                    player1TextField.setText("" + presentValueOfString);
                    player1HowManyTimes[0]++;
                    player1[player1HowManyTimes[0]] = presentValueOfString;
                }
            });

            //Player 2
            player2Plius.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    int presentValueOfString = Integer.parseInt(String.valueOf(player2TextField.getText()));
                    presentValueOfString++;
                    player2TextField.setText("" + presentValueOfString);
                    player2HowManyTimes[0]++;
                    player2[player2HowManyTimes[0]] = presentValueOfString;
                }
            });
            player2Minus.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    int presentValueOfString = Integer.parseInt(String.valueOf(player2TextField.getText()));
                    presentValueOfString--;
                    player2TextField.setText("" + presentValueOfString);
                    player2HowManyTimes[0]++;
                    player2[player2HowManyTimes[0]] = presentValueOfString;
                }
            });

            reset.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    player1TextField.setText("" + 20);
                    player2TextField.setText("" + 20);
                    /*player3TextField.setText("" + 20);
                    player4TextField.setText("" + 20);*/
                    for(int i = 0; i <= player1HowManyTimes[0]; i++) {
                        player1[i] = 0;
                    }
                    for(int i = 0; i <= player2HowManyTimes[0]; i++) {
                        player2[i] = 0;
                    }
                    player1HowManyTimes[0] = 0;
                    player2HowManyTimes[0] = 0;
                }
            });
            map.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            startActivity(mapIntent);
                        }
                    }, 1000);
                }
            });

            history.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent historyIntent = new Intent(v.getContext(), history.class);
                    startActivityForResult(historyIntent, 0);
                }
            });
            settings.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent settingsIntent = new Intent(v.getContext(), settings.class);
                    startActivityForResult(settingsIntent, 0);
                }
            });
            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //name of the record/game or data and time
                    //list how hit pts changed during the game
                    String stringToSend = parseArrayToSend(player1HowManyTimes[0], player1, player2HowManyTimes[0], player2);
                    Intent recordIntent = new Intent(v.getContext(), addhistory.class);
                    recordIntent.putExtra("stringToSend", stringToSend);
                    startActivity(recordIntent);
                }
            });
    }
    public String parseArrayToSend(int n1, int[] array1, int n2, int[] array2)
    {
        String result = "Player 1: ";
        for(int i = 0; i < n1; i++)
        {
            result += array1[i];
            result += " ";
        }
        result += "\nPlayer 2: ";
        for(int i = 0; i < n2; i++)
        {
            result += array2[i];
            result += " ";
        }
        return result;
    }
}