package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContentActivity4 extends AppCompatActivity implements View.OnClickListener{
    private TextView pagePrev, pageNext, page1, page2, page3, page4, page5;
    private ProgressBar progressBar4;
    private ScrollView scrollView;
    private Button btnQuiz4;
    private ImageButton btnScrollToTop,btnBackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ผูก UI กับตัวแปร
        btnBackHome = findViewById(R.id.Content4btnBackHome);
        pagePrev = findViewById(R.id.pagePrev);
        pageNext = findViewById(R.id.pageNext);
        progressBar4 = findViewById(R.id.progressBar4);
        scrollView = findViewById(R.id.scrollView);
        btnQuiz4 = findViewById(R.id.btnQuiz4);
        btnScrollToTop = findViewById(R.id.btnScrollToTop);
        btnScrollToTop.setVisibility(View.GONE);
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        page5 = findViewById(R.id.page5);
        setupPagination();

        // ตั้งค่าคลิก listener ให้ activity นี้จัดการเอง
        pagePrev.setOnClickListener(this);
        pageNext.setOnClickListener(this);
        btnQuiz4.setOnClickListener(this);
        btnBackHome.setOnClickListener(this);
        btnScrollToTop.setOnClickListener(v -> scrollView.smoothScrollTo(0, 0));

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view = scrollView.getChildAt(0);
            int scrollY = scrollView.getScrollY(); // ตำแหน่งที่ Scroll อยู่
            int maxScroll = view.getBottom() - scrollView.getHeight(); // ความยาวเลื่อนสูงสุด

            if (maxScroll > 0) {
                int progress = (int) (((float) scrollY / maxScroll) * 100);
                progressBar4.setProgress(progress);
            }

            if (scrollY >= maxScroll) { // ถ้าเลื่อนลงสุด
                btnScrollToTop.setVisibility(View.VISIBLE);
                showButtonSmoothly();
            } else {
                btnScrollToTop.setVisibility(View.GONE);
                hideButtonSmoothly();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == pagePrev) {
            Intent intent = new Intent(ContentActivity4.this, ContentActivity3.class);
            startActivity(intent);
        } else if (v == pageNext) {
            Intent intent = new Intent(ContentActivity4.this, ContentActivity5.class);
            startActivity(intent);
        } else if (v == page1) {
            openPage(1);
        } else if (v == page2) {
            openPage(2);
        } else if (v == page3) {
            openPage(3);
        } else if (v == page4) {
            openPage(4);
        } else if (v == page5) {
            openPage(5);
        } else if (v == btnBackHome) {
            Intent intent = new Intent(ContentActivity4.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //Intent intent = new Intent(ContentActivity4.this, Quiz4Activity.class);
            //startActivity(intent);
            //finish();
        }
    }
    private void showButtonSmoothly() {
        btnScrollToTop.animate().alpha(1f).setDuration(300).start();
        btnScrollToTop.setVisibility(View.VISIBLE);
    }

    private void hideButtonSmoothly() {
        btnScrollToTop.animate().alpha(0f).setDuration(300).withEndAction(() -> btnScrollToTop.setVisibility(View.GONE)).start();
    }

    private void setupPagination() {
        page1.setOnClickListener(view -> openPage(1));
        page2.setOnClickListener(view -> openPage(2));
        page3.setOnClickListener(view -> openPage(3));
        page4.setOnClickListener(view -> openPage(4));
        page5.setOnClickListener(view -> openPage(5));
    }

    private void openPage(int pageNumber) {
        Intent intent;
        switch (pageNumber) {
            case 1:
                intent = new Intent(ContentActivity4.this, ContentActivity1.class); // เปิดหน้า 1
                break;
            case 2:
                intent = new Intent(ContentActivity4.this, ContentActivity2.class); // เปิดหน้า 2
                break;
            case 3:
                intent = new Intent(ContentActivity4.this, ContentActivity3.class); // เปิดหน้า 3
                break;
            case 4:
                intent = new Intent(ContentActivity4.this, ContentActivity4.class); // เปิดหน้า 4
                break;
            case 5:
                intent = new Intent(ContentActivity4.this, ContentActivity5.class); // เปิดหน้า 5
                break;
            default:
                // ถ้า pageNumber เกินขอบเขต ก็เปิดหน้าแรก
                intent = new Intent(ContentActivity4.this, ContentActivity4.class);
                break;
        }
        startActivity(intent);
    }
}