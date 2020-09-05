package com.thejoonbug.splitshare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker (Context _context) {
        context = _context;
    }

    @Override
    protected String doInBackground(String... params) {
        String base_url = "http://192.168.2.37/";
        String action = params[0];

        if (action.equals("register")) {
            String register_url = base_url + "splitshare_register.php";
            try {
                String username = params[1];
                String password = params[2];
                String email = params[3];
                String phonenumber = params[4];
                String currency = params[5];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = "";
                post_data = concat_URLEncoder(post_data, "username", username, false);
                post_data = concat_URLEncoder(post_data, "password", password, false);
                post_data = concat_URLEncoder(post_data, "email", email, false);
                post_data = concat_URLEncoder(post_data, "phonenumber", phonenumber, false);
                post_data = concat_URLEncoder(post_data, "currency", currency, true);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();;
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (action.equals("login")) {
            String register_url = base_url + "splitshare_login.php";

            try {
                String username = params[1];
                String password = params[2];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = "";
                post_data = concat_URLEncoder(post_data, "username", username, false);
                post_data = concat_URLEncoder(post_data, "password", password, true);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();;
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Connecting to server");
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent;
        switch (result) {
            case "REGISTER_FAIL_USERNAME":
                alertDialog.setMessage("Username is taken");
                alertDialog.show();
                break;

            case "LOGIN_FAIL":
                alertDialog.setMessage("Username/Password are not correct");
                alertDialog.show();
                break;

            case "REGISTER_SUCCESS":
                Toast.makeText(context, "Registration complete", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, Dashboard.class);
                context.startActivity(intent);
                break;

            case "LOGIN_SUCCESS":
                Toast.makeText(context, "Login success", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, Dashboard.class);
                context.startActivity(intent);
                break;
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private String concat_URLEncoder(String initial, String key, String value, boolean last) throws UnsupportedEncodingException {
        initial = initial + URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
        if (!last) {
            initial = initial +"&";
        }
        return initial;
    }
}
