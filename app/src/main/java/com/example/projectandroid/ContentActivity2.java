package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContentActivity2 extends AppCompatActivity implements View.OnClickListener {
    private TextView btnBack, btnNext;
    private ProgressBar progressBar2;
    private ScrollView scrollView;
    private Button btnQuiz2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // ผูก UI กับตัวแปร
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        progressBar2 = findViewById(R.id.progressBar2);
        scrollView = findViewById(R.id.scrollView);
        btnQuiz2 = findViewById(R.id.btnQuiz2);

        // ตั้งค่าคลิก listener ให้ activity นี้จัดการเอง
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnQuiz2.setOnClickListener(this);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view = scrollView.getChildAt(0);
            int scrollY = scrollView.getScrollY(); // ตำแหน่งที่ Scroll อยู่
            int maxScroll = view.getBottom() - scrollView.getHeight(); // ความยาวเลื่อนสูงสุด

            if (maxScroll > 20) {
                // แปลงค่า Scroll เป็นเปอร์เซ็นต์ของ 40%
                int progress = 20 + (int) (((float) scrollY / maxScroll) * 20);
                if (scrollY >= maxScroll) {
                    progress = 40;
                }
                progressBar2.setProgress(progress);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(ContentActivity2.this, ContentActivity1.class);
            startActivity(intent);
            finish(); // ปิดหน้านี้เพื่อไม่ให้ย้อนกลับมา
        } else if (v == btnNext) {
            Intent intent = new Intent(ContentActivity2.this, ContentActivity3.class);
            startActivity(intent);
            finish();
        } else {
            //Intent intent = new Intent(ContentActivity1.this, Quiz2.class);
            //startActivity(intent);
            //finish();
        }
    }
}