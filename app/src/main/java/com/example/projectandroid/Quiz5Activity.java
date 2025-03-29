package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class Quiz5Activity extends AppCompatActivity implements View.OnClickListener {
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
            "ฟังก์ชันชื่อเดียวกันแต่พารามิเตอร์ต่าง",
            "ฟังก์ชันที่เรียกใช้ตัวเองซ้ำๆ"
    };
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    TextView questionNumber,question;
    ImageButton btnBack;
    ImageView Profile;

    int [] cardId = {R.id.quiz5_card1,R.id.quiz5_card2,R.id.quiz5_card3,R.id.quiz5_card4};
    CardView[] cardViews = new CardView[cardId.length];

    int [] textID  = {R.id.textView5_1,R.id.textView5_2,R.id.textView5_3,R.id.textView5_4};
    TextView [] textViews = new TextView[textID.length];

    int imageID [] = {R.id.heart5_1,R.id.heart5_2,R.id.heart5_3};
    ImageView heartImage [] = new ImageView[3];

    int index = 0;
    int life = 2;
    int Progress;
    userData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent launch = getIntent();
        user = (userData) launch.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile5);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);


        Progress = Integer.parseInt(part[3]);
//        user = new userData(part[0],part[1],part[2],Progress,part[4]);

        question = findViewById(R.id.quiz5_question);
        questionNumber = findViewById(R.id.titleQuestion5);
        btnBack = findViewById(R.id.btnquiz5_BackHome);
        btnBack.setOnClickListener(this);
        for(int i = 0 ; i < imageID.length;i++){
            heartImage[i] =findViewById(imageID[i]);
        }
        for(int i = 0 ;i < cardId.length;i++) {
            cardViews[i] = findViewById(cardId[i]);
            cardViews[i].setOnClickListener(this);
            textViews[i] = findViewById(textID[i]);
            textViews[i].setText(choice[index][i]);
        }
        question.setText(questions[index]);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        boolean status = false;
        if(id == R.id.btnquiz1_BackHome){
            status = true;
            finish();
        }

        for (int i = 0; i < cardId.length; i++) {
            if (id == cardId[i]) {
                if (choice[index][i] == Answer[index]) {
                    Toast.makeText(this, "ถูกต้อง!", Toast.LENGTH_SHORT).show();
                    index++;
                    questionNumber.setText("Question " + (index+1) + " : " );
//                    All Done
                    if (index == questions.length) {
                        Toast.makeText(this, "ยินดีด้วย! คุณทำครบทุกข้อแล้ว!", Toast.LENGTH_LONG).show();
                    }
                    question.setText(questions[index]);
                    SetChoice(index);
                    status = true;
                    break;
                }
            }
        }
        if (!status) {
            if (life >= 0) {
                System.out.println(life);
                heartImage[life].setImageResource(R.drawable.broken);
            }
            life--;
            if (life < 0) {
                Toast.makeText(this, "คุณแพ้แล้ว! ลองใหม่อีกครั้ง", Toast.LENGTH_LONG).show();
                ResetQuize();
            }
        }
    }
    public void SetChoice(int index){
        for(int i = 0;i < cardId.length;i++){
            textViews[i].setText(choice[index][i]+"");
        }
    }
    public void ResetQuize(){
        index = 0;
        life = 2;

        question.setText(questions[0]+"");
        questionNumber.setText("Question " + index+1 + " : ");
        for(int i = 0 ;i < cardId.length;i++){
            textViews[i].setText(choice[0][i]+"");
        }
        for(int i =0 ; i< heartImage.length;i++){
            heartImage[i].setImageResource(R.drawable.heart_1);
        }
    }
}