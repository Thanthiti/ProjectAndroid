package com.example.projectandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContentActivity1 extends AppCompatActivity implements View.OnClickListener{
    private TextView btnBack, btnNext;
    private ProgressBar progressBar;
    private ScrollView scrollView;
    private int a = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ผูก UI กับตัวแปร
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollView);

        // ตั้งค่าคลิก listener ให้ activity นี้จัดการเอง
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        //btnQuiz.setOnClickListener(this);

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view = scrollView.getChildAt(0);
            int scrollY = scrollView.getScrollY(); // ตำแหน่งที่ Scroll อยู่
            int maxScroll = view.getBottom() - scrollView.getHeight(); // ความยาวเลื่อนสูงสุด

            if (maxScroll > 0) {
                // แปลงค่า Scroll เป็นเปอร์เซ็นต์ของ 20%
                int progress = (int) (((float) scrollY / maxScroll) * 20);
                progressBar.setProgress(progress);
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            Toast.makeText(this, "Back Clicked", Toast.LENGTH_SHORT).show();
        } else if (v == btnNext) {
            Toast.makeText(this, "Next Clicked", Toast.LENGTH_SHORT).show();
        } //else if (v == btnQuiz) {
        //Intent intent = new Intent(Content1Activity.this, QuizActivity.class);
        //startActivity(intent);
        //}
    }
}