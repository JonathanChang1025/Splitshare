package com.thejoonbug.splitshare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;
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

    int MIN_USER_LENGTH = 5;
    int MIN_PASS_LENGTH = 6;
    String DEFAULT_CURRENCY = "CAD";

    ImageView iv_logo;
    TextInputLayout til_username;
    TextInputLayout til_password;
    TextInputLayout til_password_confirm;
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
        til_password_confirm = (TextInputLayout)findViewById(R.id.input_password_confirm);
        til_email = (TextInputLayout)findViewById(R.id.input_email);
        til_phonenumber = (TextInputLayout)findViewById(R.id.input_phonenumber);
        actv_currency = (AutoCompleteTextView)findViewById(R.id.actv_currency);
        btn_register = (Button)findViewById(R.id.button_register);
        btn_signin = (Button)findViewById(R.id.button_signin);

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(750);
        til_password_confirm.startAnimation(animation);
        til_email.startAnimation(animation);
        til_phonenumber.startAnimation(animation);

        setCurrencyAdapter();

    }

    private void setCurrencyAdapter() {
        String[] CURRENCY = new String[] {"CAD", "USD", "EUR", "NTD"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, CURRENCY);
        actv_currency.setAdapter(adapter);
        actv_currency.setHint(DEFAULT_CURRENCY);
        actv_currency.setHintTextColor(getResources().getColor(R.color.colorLightGray));
        
    }

    @Override
    public void onBackPressed() {
        btn_signin_clicked(null);
    }

    public void btn_register_clicked(View v) {
        String action = "register";

        String username = til_username.getEditText().getText().toString();
        String password = til_password.getEditText().getText().toString();
        String password_confirm = til_password_confirm.getEditText().getText().toString();
        String email = til_email.getEditText().getText().toString();
        String phonenumber = til_phonenumber.getEditText().getText().toString();
        String currency = actv_currency.getText().toString();

        if (currency.isEmpty()) {
            currency = DEFAULT_CURRENCY;
        }

        // Some text validation before connecting to sql server
        if (username.length() < MIN_USER_LENGTH) {
            Toast.makeText(this, "Username must be at least " + MIN_USER_LENGTH + " characters long", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.length() < MIN_PASS_LENGTH) {
            Toast.makeText(this, "Password must be at least " + MIN_PASS_LENGTH + " characters long", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(password_confirm)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        } else if (email.length() > 0 && !isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        } else if (!(phonenumber.length() == 0 || phonenumber.length() == 10)) {
            Toast.makeText(this, "Please enter 10 digit phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(action, username, password, email, phonenumber, currency);

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

    private static boolean isValidEmail(CharSequence input) {
        return (!TextUtils.isEmpty(input) && Patterns.EMAIL_ADDRESS.matcher(input).matches());
    }


}
