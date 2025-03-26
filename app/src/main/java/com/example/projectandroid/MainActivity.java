package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    //Button btnProgress;
    LinearLayout menu_data , menu_edit;
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
        //btnProgress = findViewById(R.id.home_btnProgress);
        //btnProgress.setOnClickListener(this);
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
        if (view.getId() == R.id.home_menu_data) Toast.makeText(this, "test" , Toast.LENGTH_SHORT).show();
        else if (view.getId() == R.id.home_menu_edit) {
            Intent i = new Intent(this , EditActivity.class);
            startActivity(i);
        } else if (view.getId() == R.id.home_logout) {
            
        }
        //if (view.getId() == R.id.home_btnProgress) {
            //if (exp >= 0 && exp < 100) {
               // progressBar.incrementProgressBy(20);
               // exp += 20;
               // textExp.setText("Your experience is : " + exp + " %");
           // } else Toast.makeText(this , "MAX" , Toast.LENGTH_SHORT).show();
       // }
   }
}