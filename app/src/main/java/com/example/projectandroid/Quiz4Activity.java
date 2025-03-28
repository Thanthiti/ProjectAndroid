package com.example.projectandroid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz4Activity extends AppCompatActivity {
    String[] questions = {
            "1. ผลลัพธ์ของโค้ดต่อไปนี้คืออะไร?" + "int number = 20;\nif (number > 5) {\n  System.out.println(\"Greater than 5\");\n}",
            "2. ถ้าต้องการวนซ้ำจำนวนครั้งที่แน่นอน ควรใช้คำสั่งใด?\n",
            "3. เติมคำสั่งที่เหมาะสมเพื่อแสดง \"Hello World\" ถ้า x มากกว่า y",
                    "if (x > y) {\n  // ใส่คำสั่งที่เหมาะสม\n}",
            "4. คำสั่ง else if ใช้เพื่อตรวจสอบเงื่อนไขใหม่เมื่อเงื่อนไขแรกใน if เป็น:\n",
            "5. ลูปต่อไปนี้จะทำงานกี่ครั้ง?\n" + "for (int i = 0; i < 5; i++) {\n  System.out.println(i);\n}"
    };
    String choice [][] = {{"น้อยกว่า 5","มากกว่า 5","เกิดข้อผิดพลาด","ไม่มีผลลัพธ์"},
            {"for loop","while loop","do-while loop","boolean"},
            {"if, <","if, >","else if, <","else if, >"},
            {"true","false","int","float"},
            {"2","3","4","5"}
    };
    String Answer [] = {
            "มากกว่า 5",
            "for loop",
            "if, >",
            "false",
            "4"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}