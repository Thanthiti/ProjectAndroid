package com.example.projectandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz3Activity extends AppCompatActivity {
    CardView card1;
    String[] questions = {
            "1. ตัวดำเนินการ (Operators) ใช้เพื่อ:",
            "2. คูณ 10 ด้วย 5 และพิมพ์ผลลัพธ์ออกมา:  \n" +
                    "System.out.println(10 __ 5);",
            "3. ใช้ตัวดำเนินการที่ถูกต้องเพื่อเพิ่มค่าของตัวแปร x ขึ้นทีละ 1 \n" +
                    "int x = 10;\n" +
                    "__x;",
            "4. ใช้ตัวดำเนินการกำหนดค่า (+=) เพื่อเพิ่มค่า 5 ให้กับตัวแปร x  \n" +
                    "int x = 10;\n" +
                    "x __ 5;",
            "5. ตัวดำเนินการตรรกศาสตร์ && (AND) จะคืนค่า true ในกรณีใด? "
    };
    String choice [][] = {{"สร้างค่าคงที่และค่าตัวแปร","ดำเนินการกับตัวแปรและค่า","สร้างออบเจ็กต์และคลาส","เข้าถึงคอมเมนต์เพื่อแสดงบนหน้าจอ"},
            {"+","-","*","/"},
            {"++","--","**","//"},
            {"=-","-=","=+","+="},
            {"เมื่อเงื่อนไขอย่างน้อยหนึ่งเป็น true","เมื่อทุกเงื่อนไขเป็น true","เมื่อเงื่อนไขอย่างน้อยหนึ่งเป็น false","เมื่อเงื่อนไขอย่างน้อยหนึ่งเป็น false"}
    };
    String Answer [] = {
            "ดำเนินการกับตัวแปรและค่า",
            "*",
            "++",
            "+=",
            "เมื่อทุกเงื่อนไขเป็น true"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //card1 = findViewById(R.id.quiz3_card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Quiz3Activity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }
}