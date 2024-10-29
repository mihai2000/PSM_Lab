package com.upt.cti.lifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";

    Integer restarts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate, this=" + this);
        if(savedInstanceState != null)
            Log.d(TAG, savedInstanceState.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            TextFragment fragment = TextFragment.newInstance("1", "2");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        }
        else {
            TextFragment fragment = (TextFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.content);
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        TextView v = findViewById(R.id.noRestarts);
        if( v != null )
            v.setText(restarts.toString());
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onRestart() {
        Log.d(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState" + restarts);
        restarts = restarts + 1;
        outState.putInt("restarts", restarts);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        restarts = savedInstanceState.getInt("restarts", 0);
    }
}