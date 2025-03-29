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
    userData user;
    // Alert Dialog
    Dialog dialog;
    Button btnOkWinner;
    // icon toast
    int iconAlerttoast [] = {R.drawable.report_check , R.drawable.report_incorrect};
    String name,email,password,progress,profile;
    ManageFile edtProgress;
    final String filename = "User.txt";
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
        name = part[0];
        email = part[1];
        password = part[2];
        progress = part[3]+"5";
        profile = part[4];

        // Dialog Alert
        dialog = new Dialog(Quiz5Activity.this);
        dialog.setContentView(R.layout.alertbox_winner);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);

        btnOkWinner = dialog.findViewById(R.id.example_alert_ok);
        btnOkWinner.setOnClickListener(this);
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

        for (int i = 0; i < cardId.length; i++) {
            if (id == cardId[i]) {
                if (choice[index][i] == Answer[index]) {
                    showToast("Correct!" , 0);
                    index++;
                    if (index == questions.length) {

                        Toast.makeText(this, "ยินดีด้วย! คุณทำครบทุกข้อแล้ว!", Toast.LENGTH_LONG).show();
                        status = true;
                        dialog.show();
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