package com.thejoonbug.splitshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class CreateAccountActivity extends AppCompatActivity {

    ImageView iv_logo;
    TextInputLayout til_username;
    TextInputLayout til_password;
    TextInputLayout til_email;
    TextInputLayout til_phonenumber;
    AutoCompleteTextView actv_currency;
    Button btn_register;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();

        iv_logo = (ImageView)findViewById(R.id.logo);
        til_username = (TextInputLayout)findViewById(R.id.input_username);
        til_password = (TextInputLayout)findViewById(R.id.input_password);
        til_email = (TextInputLayout)findViewById(R.id.input_email);
        til_phonenumber = (TextInputLayout)findViewById(R.id.input_phonenumber);
        actv_currency = (AutoCompleteTextView)findViewById(R.id.actv_currency);
        btn_register = (Button)findViewById(R.id.button_register);
        btn_signin = (Button)findViewById(R.id.button_signin);

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(750);
        til_email.startAnimation(animation);
        til_phonenumber.startAnimation(animation);

        setCurrencyAdapter();

    }

    private void setCurrencyAdapter() {
        String[] CURRENCY = new String[] {"CAD", "USD", "EUR", "NTD"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, CURRENCY);
        actv_currency.setAdapter(adapter);
        actv_currency.setHint(CURRENCY[0]);
        actv_currency.setHintTextColor(getResources().getColor(R.color.colorLightGray));



    }

    @Override
    public void onBackPressed() {
        btn_signin_clicked(null);
    }

    public void btn_signin_clicked(View v) {
        //super.onBackPressed();
        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);

        Pair[] pairss = new Pair[5];
        pairss[0] = new Pair<View, String>(iv_logo, "logoTransition");
        pairss[1] = new Pair<View, String>(til_username, "usernameTransition");
        pairss[2] = new Pair<View, String>(til_password, "passwordTransition");
        pairss[3] = new Pair<View, String>(btn_register, "submitTransition");
        pairss[4] = new Pair<View, String>(btn_signin, "reginTransition");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pairss);
        //ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, iv_logo, "logoTransition");
        startActivity(intent, activityOptions.toBundle());
    }
}
