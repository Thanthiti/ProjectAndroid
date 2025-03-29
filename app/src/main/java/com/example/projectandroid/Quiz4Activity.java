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

public class Quiz4Activity extends AppCompatActivity implements View.OnClickListener{
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
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    TextView questionNumber,question;
    ImageButton btnBack;
    ImageView Profile;

    int [] cardId = {R.id.quiz4_card1,R.id.quiz4_card2,R.id.quiz4_card3,R.id.quiz4_card4};
    CardView[] cardViews = new CardView[cardId.length];

    int [] textID  = {R.id.textView4_1,R.id.textView4_2,R.id.textView4_3,R.id.textView4_4};
    TextView [] textViews = new TextView[textID.length];

    int imageID [] = {R.id.heart4_1,R.id.heart4_2,R.id.heart4_3};
    ImageView heartImage [] = new ImageView[3];

    int index = 0;
    int life = 2;
    int Progress;
    userData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent launch = getIntent();
        user = (userData) launch.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile4);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);


        Progress = Integer.parseInt(part[3]);
//        user = new userData(part[0],part[1],part[2],Progress,part[4]);

        question = findViewById(R.id.quiz4_question);
        questionNumber = findViewById(R.id.titleQuestion4);
        btnBack = findViewById(R.id.btnquiz4_BackHome);
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