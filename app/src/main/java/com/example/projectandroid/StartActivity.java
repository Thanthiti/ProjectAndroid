package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnGetStart;
    TextView textSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnGetStart = findViewById(R.id.Start_btn_start);
        btnGetStart.setOnClickListener(this);
        textSignIn = findViewById(R.id.start_text_signin);
        textSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Start_btn_start) {
            Intent i = new Intent(this , RegisterActivity.class);
            startActivity(i);
        } else if (view.getId() == R.id.start_text_signin) {
            Intent i = new Intent(this , LoginActivity.class);
            startActivity(i);
        }
    }
}