package com.example.projectandroid;

import android.annotation.SuppressLint;
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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Arrays;

public class Quiz2Activity extends AppCompatActivity implements  View.OnClickListener{
    String[] questions = {
            "ในการสร้างตัวแปรใน Java คุณต้องกำหนดประเภทข้อมูลก่อนเสมอหรือไม่?",
            "กำหนดชื่อตัวแปรเป็น carName และกำหนดค่า \"Volvo\" ให้กับมัน",
            "ข้อใดถูกต้องที่สุดในการสร้างตัวแปร maxSpeed และกำหนดค่า 120 ให้กับมัน",
            "เมธอดใดที่ใช้พิมพ์ค่าตัวแปรออกทางหน้าจอบ่อยที่สุด?",
            "เติมคำที่ขาดหายไป\n" +
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

    TextView questionNumber,question,textUsername;
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
    userData user;
    // Alert Dialog
    Dialog dialogWin , dialogLose;
    Button btnOkWinner , btnYes , btnNo;
    // icon toast
    int iconAlerttoast [] = {R.drawable.report_check , R.drawable.report_incorrect};
    String name,email,password,progress,profile;
    ManageFile edtProgress;
    final String filename = "User.txt";
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
        textUsername = findViewById(R.id.quiz2textUsername);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);
        name = part[0];
        email = part[1];
        password = part[2];
        progress = part[3]+"2";
        profile = part[4];
        textUsername.setText(name+"");

        // Dialog Alert When User Win
        dialogWin = new Dialog(Quiz2Activity.this);
        dialogWin.setContentView(R.layout.alertbox_winner);
        dialogWin.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogWin.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialogWin.setCancelable(false);
        btnOkWinner = dialogWin.findViewById(R.id.example_alert_ok);
        btnOkWinner.setOnClickListener(this);

        // Dialog Alert When User Lose
        dialogLose = new Dialog(Quiz2Activity.this);
        dialogLose.setContentView(R.layout.alertbox_lose);
        dialogLose.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogLose.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialogLose.setCancelable(false);

        btnNo = dialogLose.findViewById(R.id.example_alert_no);
        btnYes = dialogLose.findViewById(R.id.example_alert_yes);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ถ้ากด yes
                ResetQuize();
            }
        });

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
            textViews[i].setText(choice[0][i]);
        }
        question.setText(questions[0]);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        boolean status = false;
        if(id == R.id.btnquiz1_BackHome){
            status = true;
            finish();
        }else if(id == R.id.example_alert_ok){
            edtProgress = new ManageFile(this,name,email,password,progress,profile,filename);
            edtProgress.UpdateData(name,email,password,profile,false);
            user = new userData(name,email,password,progress,profile);
            status = true;
            Intent launch = new Intent(this,MainActivity.class);
            launch.putExtra("user",user);
            startActivity(launch);
        }
        else if(id == R.id.example_alert_no){
            user = new userData(name,email,password,progress,profile);
            status = true;
            Intent launch = new Intent(this, ContentActivity1.class);
            launch.putExtra("user",user);
            startActivity(launch);
        }

        for (int i = 0; i < cardId.length; i++) {
            if (id == cardId[i]) {
                if (choice[index][i] == Answer[index]) {
                    showToast("Correct!" , 0);
                    index++;
                    if (index == questions.length) {

                        Toast.makeText(this, "ยินดีด้วย! คุณทำครบทุกข้อแล้ว!", Toast.LENGTH_LONG).show();
                        status = true;
                        dialogWin.show();
                        break;
                    }
                    question.setText(questions[index]);
                    questionNumber.setText("Question " + (index+1) + " : " );
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