package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class ContentActivity1 extends AppCompatActivity implements View.OnClickListener{
    private TextView pagePrev, pageNext, page1, page2, page3, page4, page5;
    private ProgressBar progressBar;
    private ScrollView scrollView;
    private Button btnQuiz1;
    private ImageButton btnScrollToTop,btnBackHome;
    private TextView Username;
    private ImageView Profile;
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};
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
        Intent i = getIntent();
        userData user = (userData) i.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Username = findViewById(R.id.Content1Username);
        Profile = findViewById(R.id.Content1profileImage);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);
        Username.setText(""+part[0]);

        btnBackHome = findViewById(R.id.Content1btnBackHome);
        pagePrev = findViewById(R.id.pagePrev);
        pageNext = findViewById(R.id.pageNext);
        progressBar = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollView);
        btnQuiz1 = findViewById(R.id.btnQuiz1);
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
        btnQuiz1.setOnClickListener(this);
        btnBackHome.setOnClickListener(this);
        btnScrollToTop.setOnClickListener(v -> scrollView.smoothScrollTo(0, 0));

        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            View view = scrollView.getChildAt(0);
            int scrollY = scrollView.getScrollY(); // ตำแหน่งที่ Scroll อยู่
            int maxScroll = view.getBottom() - scrollView.getHeight(); // ความยาวเลื่อนสูงสุด

            if (maxScroll > 0) {
                int progress = (int) (((float) scrollY / maxScroll) * 100);
                progressBar.setProgress(progress);
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
        } else if (v == pageNext) {
            Intent intent = new Intent(ContentActivity1.this, ContentActivity2.class);
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
            finish();
        } else {
            Intent intent = new Intent(ContentActivity1.this, Quiz1Activity.class);
            startActivity(intent);
        }
    }
    //show smooth btn
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
                intent = new Intent(ContentActivity1.this, ContentActivity1.class); // เปิดหน้า 1
                break;
            case 2:
                intent = new Intent(ContentActivity1.this, ContentActivity2.class); // เปิดหน้า 2
                break;
            case 3:
                intent = new Intent(ContentActivity1.this, ContentActivity3.class); // เปิดหน้า 3
                break;
            case 4:
                intent = new Intent(ContentActivity1.this, ContentActivity4.class); // เปิดหน้า 4
                break;
            case 5:
                intent = new Intent(ContentActivity1.this, ContentActivity5.class); // เปิดหน้า 5
                break;
            default:
                // ถ้า pageNumber เกินขอบเขต ก็เปิดหน้าแรก
                intent = new Intent(ContentActivity1.this, ContentActivity1.class);
                break;
        }
        startActivity(intent);
    }
}