package com.thejoonbug.splitshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    public void onBackPressed() {
    }
}
