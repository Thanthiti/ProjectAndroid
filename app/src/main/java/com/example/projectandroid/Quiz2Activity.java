package com.example.projectandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz2Activity extends AppCompatActivity {
    String[] questions = {
            "1. ในการสร้างตัวแปรใน Java คุณต้องกำหนดประเภทข้อมูลก่อนเสมอหรือไม่?",
            "2. กำหนดชื่อตัวแปรเป็น carName และกำหนดค่า \"Volvo\" ให้กับมัน",
            "3. ข้อใดถูกต้องที่สุดในการสร้างตัวแปร maxSpeed และกำหนดค่า 120 ให้กับมัน",
            "4. เมธอดใดที่ใช้พิมพ์ค่าตัวแปรออกทางหน้าจอบ่อยที่สุด?",
            "5. เติมคำที่ขาดหายไป เพื่อรวมข้อความและตัวแปรเข้าด้วยกัน**\n" +
                    "String name = \"John\";\n" +
                    "System.out.println(\"Hello \"____);"
    };
    String choice [][] = {{"ใช่ต้องกำหนดประเภทข้อมูลก่อนเสมอ","ไม่จำเป็นต้องกำหนดประเภทข้อมูล","ขึ้นอยู่กับชนิดของตัวแปร","ไม่จำเป็นใน Java"},
            {"char carName = 'Volvo';","string carName = 'Volvo';","String carName = \"Volvo\";","string carName = \"Volvo\";"},
            {"int maxSpeed = 120;","float maxSpeed = 120;","double maxSpeed = 120;","long maxSpeed = 120;"},
            {"printline()","printvar()","echo()","println()"},
            {"+ John","+ \"John\"","+ name","+ String name"}
    };
    String Answer [] = {
            "ใช่, ต้องกำหนดประเภทข้อมูลก่อนเสมอ",
            "String carName = \"Volvo\";",
            "int maxSpeed = 120;",
            "println()",
            "+ name"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}