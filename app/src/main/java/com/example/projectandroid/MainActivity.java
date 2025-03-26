package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    //Button btnProgress;
    LinearLayout menu_data , menu_edit;
    ConstraintLayout reportExp;
    ImageView logout ;
    int exp = 0;
    TextView textExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressBar = findViewById(R.id.home_progressbar);
        reportExp = findViewById(R.id.home_report_exp);
        reportExp.setOnClickListener(this);
        textExp = findViewById(R.id.home_textExp);
        menu_data = findViewById(R.id.home_menu_data);
        menu_data.setOnClickListener(this);
        menu_edit = findViewById(R.id.home_menu_edit);
        menu_edit.setOnClickListener(this);
        logout = findViewById(R.id.home_logout);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.home_menu_data)
            Toast.makeText(this, "test" , Toast.LENGTH_SHORT).show();
        else if (view.getId() == R.id.home_menu_edit) {
            Intent i = new Intent(this , EditActivity.class);
            startActivity(i);
        } else if (view.getId() == R.id.home_report_exp) {
            Intent i = new Intent(this , ReportActivity.class );
            startActivity(i);
        } else if (view.getId() == R.id.home_logout) {
            finish();
        }
   }
}