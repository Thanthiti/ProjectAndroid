package com.example.projectandroid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz5Activity extends AppCompatActivity {
    String[] questions = {
            "1. ฟังก์ชันใน Java คืออะไร?",
            "2. คำว่า void ในการประกาศฟังก์ชันหมายถึงอะไร?",
            "3. ฟังก์ชันต่อไปนี้จะคืนค่ากลับมาเป็นอะไร?\n" +
                    "static int multiply(int a, int b) {\n" +
                    "    return a * b;\n",
            "4. ฟังก์ชันแบบ Overloading หมายถึงอะไร?",
            "5. ฟังก์ชันแบบ Recursive คืออะไร?"
    };

    String choice [][] = {{"ตัวแปรชนิดหนึ่ง","กลุ่มของโค้ดที่ทำงานเฉพาะอย่าง","คำสั่งสำหรับพิมพ์ข้อความ","ตัวดำเนินการทางคณิตศาสตร์"},
            {"ฟังก์ชันจะส่งค่ากลับเสมอ","ฟังก์ชันไม่มีค่าที่ส่งกลับ","ฟังก์ชันต้องมีพารามิเตอร์เสมอ","ฟังก์ชันไม่สามารถใช้ใน main ได้"},
            {"void","ค่าตัวเลข (int)","ค่าข้อความ (String)","ไม่สามารถบอกได้"},
            {"ฟังก์ชันที่มีการเรียกซ้ำ","ฟังก์ชันชื่อเดียวกันแต่พารามิเตอร์ต่างกัน","ฟังก์ชันที่ไม่มีพารามิเตอร์","ฟังก์ชันที่ไม่สามารถใช้ return ได้"},
            {"ฟังก์ชันที่เรียกใช้ตัวเองซ้ำๆ","ฟังก์ชันที่ไม่รับพารามิเตอร์","ฟังก์ชันที่มีแค่ if-else","ฟังก์ชันที่ใช้ switch-case"}
    };
    String Answer [] = {
            "กลุ่มของโค้ดที่ทำงานเฉพาะอย่าง",
            "ฟังก์ชันไม่มีค่าที่ส่งกลับ",
            "ค่าตัวเลข (int)",
            "ฟังก์ชันชื่อเดียวกันแต่พารามิเตอร์ต่างกัน",
            "ฟังก์ชันที่เรียกใช้ตัวเองซ้ำๆ"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}