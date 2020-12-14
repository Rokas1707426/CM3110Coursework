package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatDelegate;

public class settings extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final Button darkMode = findViewById(R.id.darkMode);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                darkMode.setText("Disable Dark Mode");
            }
        else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                darkMode.setText("Enable Dark Mode");
            }

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view)
                {
                    if (isDarkModeOn) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        editor.putBoolean("isDarkModeOn", false);
                        editor.apply();
                        // change text of Button
                        darkMode.setText("Enable Dark Mode");
                    }
                    else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        editor.putBoolean("isDarkModeOn", true);
                        editor.apply();
                        darkMode.setText("Disable Dark Mode");
                    }
                }
        });
    }
}
