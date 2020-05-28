package com.example.cse.ayush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void ButtonSignUp(View view) {
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    public void ButtonLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}
