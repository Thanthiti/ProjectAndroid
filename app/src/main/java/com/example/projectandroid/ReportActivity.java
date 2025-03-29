package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnBack;
    Set unique;
    int imageBtn [] = {R.id.Reportimagebtn1,R.id.Reportimagebtn2,R.id.Reportimagebtn3,R.id.Reportimagebtn4,R.id.ReportimageBtn5};
    ImageButton imagesCheck [] = new ImageButton[imageBtn.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Integer> receivedList = getIntent().getIntegerArrayListExtra("uniq");
        unique = new LinkedHashSet<>(receivedList);
        receivedList = new ArrayList<>(unique);

        for(int i = 0 ;i < imageBtn.length;i++){
            imagesCheck[i] = findViewById(imageBtn[i]);
                for(int Check : receivedList){
                    if(i+1 == Check){
                        imagesCheck[i].setImageResource(R.drawable.report_check);
                    }
            }
        }
        btnBack = findViewById(R.id.report_btn_back);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.report_btn_back) finish();
    }
}