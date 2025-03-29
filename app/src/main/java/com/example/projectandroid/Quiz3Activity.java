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

public class Quiz3Activity extends AppCompatActivity implements View.OnClickListener{
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
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    TextView questionNumber,question;
    ImageButton btnBack;
    ImageView Profile;

    int [] cardId = {R.id.quiz3_card1,R.id.quiz3_card2,R.id.quiz3_card3,R.id.quiz3_card4};
    CardView [] cardViews = new CardView[cardId.length];

    int [] textID  = {R.id.textView3_1,R.id.textView3_2,R.id.textView3_3,R.id.textView3_4};
    TextView [] textViews = new TextView[textID.length];

    int imageID [] = {R.id.heart3_1,R.id.heart3_2,R.id.heart3_3};
    ImageView heartImage [] = new ImageView[3];

    int index = 0;
    int life = 2;
    int Progress;
    userData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent launch = getIntent();
        user = (userData) launch.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile3);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);


        Progress = Integer.parseInt(part[3]);
//        user = new userData(part[0],part[1],part[2],Progress,part[4]);

        question = findViewById(R.id.quiz3_question);
        questionNumber = findViewById(R.id.titleQuestion3);
        btnBack = findViewById(R.id.btnquiz3_BackHome);
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