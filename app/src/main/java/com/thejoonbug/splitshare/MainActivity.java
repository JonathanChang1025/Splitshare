package com.thejoonbug.splitshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    final int MINIMUM_LOGIN_LENGTH = 6;

    private ImageView iv_logo;
    private TextInputLayout til_username;
    private TextInputLayout til_password;
    private Button btn_login;
    private Button btn_create;

    private static String ip = "b6ggdiwfbdh7mjuypfve-mysql.services.clever-cloud.com";
    private static String port = "3306";
    //private static String classes = "com.mysql.jdbc.Driver";
    private static String classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "b6ggdiwfbdh7mjuypfve";
    private static String username = "ug2tmdiev70mdel3";
    private static String password = "dfw4II0iWROw6elCdnw5";
    private static String url = "jdbc:jtds:mysql://"+ip+":"+port+"/"+database;

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

    private boolean validate_string_length(String input) {
        if (input.length() >= MINIMUM_LOGIN_LENGTH) {
            return true;
        } else {
            Toast.makeText(this, String.format("Username/Password should be at least %2d characters", MINIMUM_LOGIN_LENGTH), Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    
    public void btn_login_clicked(View v) {
        String username = til_username.getEditText().getText().toString();
        String password = til_password.getEditText().getText().toString();

        if (validate_string_length(username) && validate_string_length(password)) {
            Toast.makeText(this, username + " " + password, Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_create_clicked(View v) {
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);

        Pair[] pairs = new Pair[5];
        pairs[0] = new Pair<View, String>(iv_logo, "logoTransition");
        pairs[1] = new Pair<View, String>(til_username, "usernameTransition");
        pairs[2] = new Pair<View, String>(til_password, "passwordTransition");
        pairs[3] = new Pair<View, String>(btn_login, "submitTransition");
        pairs[4] = new Pair<View, String>(btn_create, "reginTransition");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, activityOptions.toBundle());
    }
}
