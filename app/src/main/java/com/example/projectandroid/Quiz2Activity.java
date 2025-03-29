package com.example.projectandroid;

import android.annotation.SuppressLint;
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

public class Quiz2Activity extends AppCompatActivity implements  View.OnClickListener{
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
            "ใช่ต้องกำหนดประเภทข้อมูลก่อนเสมอ",
            "String carName = \"Volvo\";",
            "int maxSpeed = 120;",
            "println()",
            "+ name"
    };
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    TextView questionNumber,question;
    ImageButton btnBack;
    ImageView Profile;

    int [] cardId = {R.id.quiz2_card1,R.id.quiz2_card2,R.id.quiz2_card3,R.id.quiz2_card4};
    CardView[] cardViews = new CardView[cardId.length];

    int [] textID  = {R.id.textView2_1,R.id.textView2_2,R.id.textView2_3,R.id.textView2_4};
    TextView [] textViews = new TextView[textID.length];

    int imageID [] = {R.id.heart2_1,R.id.heart2_2,R.id.heart2_3};
    ImageView heartImage [] = new ImageView[3];

    int index = 0;
    int life = 2;
    int Progress;
    userData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent launch = getIntent();
        user = (userData) launch.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile2);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);


        Progress = Integer.parseInt(part[3]);
//        user = new userData(part[0],part[1],part[2],Progress,part[4]);

        question = findViewById(R.id.quiz2_question);
        questionNumber = findViewById(R.id.titleQuestion2);
        btnBack = findViewById(R.id.btnquiz2_BackHome);
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