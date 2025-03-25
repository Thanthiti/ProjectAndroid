package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button btnSignUp, btnRegister;
    TextInputEditText editName,editPass;
    final String filename = "User.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnRegister = findViewById(R.id.LoginbtnRegister);
        btnSignUp = findViewById(R.id.LoginbtnSignup);
        btnSignUp.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        editName = findViewById(R.id.LogineditName);
        editPass = findViewById(R.id.LogineditPass);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.LoginbtnSignup){
            Intent launch = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(launch);
        }
        else if(id == R.id.LoginbtnRegister){
            Intent launch = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(launch);
        }
    }
}