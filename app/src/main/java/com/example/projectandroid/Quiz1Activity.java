package com.example.projectandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectandroid.R;

import java.util.ArrayList;
import java.util.List;

public class Quiz1Activity extends AppCompatActivity {
    String[] questions = {
            "1. int ใน Java คืออะไร?",
            "2. เติมประเภทข้อมูลที่ถูกต้องสำหรับตัวแปรต่อไปนี้:\n_____ myLetter = 'A';",
            "3. byte, short, int, long, float, double, boolean และ char เรียกว่า: ______ data types.",
            "4. ประเภทข้อมูลจำนวนเต็ม (Integer types) ใช้เก็บค่าอะไร?",
            "5. ข้อใด 'ไม่ใช่' ประเภทข้อมูลจำนวนเต็มที่ถูกต้อง?"
    };

    String choice [][] = {{"ประเภทข้อมูลที่ใช้แทนจำนวนเต็ม","ประเภทข้อมูลที่ใช้แทนข้อความ","ประเภทข้อมูลที่ใช้แทนเลขทศนิยม","ประเภทข้อมูลที่ใช้แทนอักขระ"},
            {"int","float","charr","String"},
            {"Primitive","Non-primitive","Prinmary","Non-primary"},
            {"เลขทศนิยม","จำนวนที่มีจุดทศนิยม","จำนวนเต็ม","จำนวนติดลบ"},
            {"byte","size","short","double"}
    };
    String Answer [] = {
            "ประเภทข้อมูลที่ใช้แทนจำนวนเต็ม",
            "char",
            "Primitive",
            "จำนวนเต็ม",
            "size"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
