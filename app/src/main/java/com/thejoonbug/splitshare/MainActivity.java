package com.thejoonbug.splitshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    ImageView iv_logo;
    TextInputLayout til_username;
    TextInputLayout til_password;
    Button btn_login;
    Button btn_create;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        iv_logo = (ImageView)findViewById(R.id.logo);
        til_username = (TextInputLayout)findViewById(R.id.input_username);
        til_password = (TextInputLayout)findViewById(R.id.input_password);
        btn_login = (Button)findViewById(R.id.button_login);
        btn_create = (Button)findViewById(R.id.button_create);
        
    }

    @Override
    public void onBackPressed() {
        til_username.clearFocus();
        til_password.clearFocus();
    }
    
    public void btn_login_clicked(View v) {
        Toast.makeText(this, "login button clicked", Toast.LENGTH_SHORT).show();
    }

    public void btn_create_clicked(View v) {
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(iv_logo, "logoTransition");
        pairs[1] = new Pair<View, String>(til_username, "usernameTransition");
        pairs[2] = new Pair<View, String>(til_password, "passwordTransition");
        pairs[3] = new Pair<View, String>(btn_login, "buttonTransition");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, activityOptions.toBundle());
    }
}
