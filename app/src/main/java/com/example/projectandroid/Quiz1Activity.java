package com.example.projectandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;


public class Quiz1Activity extends AppCompatActivity implements
View.OnClickListener{
    String[] questions = {
            "int ใน Java คืออะไร?",
            "เติมประเภทข้อมูลที่ถูกต้องสำหรับตัวแปรต่อไปนี้:\n_____ myLetter = 'A';",
            "byte, short, int, long, float, double, boolean และ char เรียกว่า: ______ data types.",
            "ประเภทข้อมูลจำนวนเต็ม (Integer types) ใช้เก็บค่าอะไร?",
            "ข้อใด 'ไม่ใช่' ประเภทข้อมูลจำนวนเต็มที่ถูกต้อง?"
    };

    String choice [][] = {{"ประเภทข้อมูลที่ใช้แทนจำนวนเต็ม","ประเภทข้อมูลที่ใช้แทนข้อความ","ประเภทข้อมูลที่ใช้แทนเลขทศนิยม","ประเภทข้อมูลที่ใช้แทนอักขระ"},
            {"int","float","char","String"},
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
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    TextView questionNumber,question;
    ImageButton btnBack;
    ImageView Profile;

    int [] cardId = {R.id.quiz1_card1,R.id.quiz1_card2,R.id.quiz1_card3,R.id.quiz1_card4};
    CardView [] cardViews = new CardView[cardId.length];

    int [] textID  = {R.id.textView1_1,R.id.textView1_2,R.id.textView1_3,R.id.textView1_4};
    TextView [] textViews = new TextView[textID.length];

    int imageID [] = {R.id.heart1_1,R.id.heart1_2,R.id.heart1_3};
    ImageView heartImage [] = new ImageView[3];

    int index = 0;
    int life = 2;
    int Progress;
    userData user;
    // Alert Dialog
    Dialog dialog;
    Button btnOkWinner;
    // icon toast
    int iconAlerttoast [] = {R.drawable.report_check , R.drawable.report_incorrect};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Dialog Alert
        dialog = new Dialog(Quiz1Activity.this);
        dialog.setContentView(R.layout.alertbox_winner);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);

        btnOkWinner = dialog.findViewById(R.id.example_alert_ok);

        btnOkWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Quiz1Activity.this , MainActivity.class);
                startActivity(i);
            }
        });
        //

        Intent launch = getIntent();
        user = (userData) launch.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile1);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);


        Progress = Integer.parseInt(part[3]);
//        user = new userData(part[0],part[1],part[2],Progress,part[4]);

        question = findViewById(R.id.quiz1_question);
        questionNumber = findViewById(R.id.titleQuestion1);
        btnBack = findViewById(R.id.btnquiz1_BackHome);
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
                    // toast correct
                    showToast("Correct!" , 0);
                    index++;
                    questionNumber.setText("Question " + (index+1) + " : " );
//                    All Done
                    if (index == questions.length) {
                        Toast.makeText(this, "ยินดีด้วย! คุณทำครบทุกข้อแล้ว!", Toast.LENGTH_LONG).show();
                        //dialog.show();
                    }
                    question.setText(questions[index]);
                    SetChoice(index);
                    status = true;
                    break;
                }
            }
        }
        if (!status) {
            // toast incorrect
            showToast("Incorrect!" , 1);
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

    // TOAST SUCCESS
    public void showToast(String message , int pic) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(
                R.layout.custom_toast,
                findViewById(R.id.custom_toast_container)
        );

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);
        ImageView image = layout.findViewById(R.id.toast_icon);
        image.setImageResource(iconAlerttoast[pic]);


        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.setView(layout);
        toast.show();
    }
    //


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
