package com.example.demoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText usernameV = (EditText)findViewById(R.id.username);
        EditText passwordV = (EditText)findViewById(R.id.password);
        String username = usernameV.getText().toString();
        String password = passwordV.getText().toString();

        boolean flag = false;
        if(!username.isEmpty() && !password.isEmpty()) {
            if(username.equals("Admin") && password.equals("Admin"))
                flag = true;
        }
        if(flag) {
            Intent redirect = new Intent(MainActivity.this, ViewActivity.class);
            startActivity(redirect);
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Invalid Username or Password")
                    .setCancelable(true)
                    .setPositiveButton(
                            "OK", (dialog, id) -> dialog.cancel())
                    .show();
        }

    }
}