package com.thejoonbug.splitshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout til_username;
    TextInputLayout til_password;
    Button btn_login;
    Button btn_create;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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
        Toast.makeText(this, "create account clicked", Toast.LENGTH_SHORT).show();
    }
}
