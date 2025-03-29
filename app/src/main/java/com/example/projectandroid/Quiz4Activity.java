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

public class Quiz4Activity extends AppCompatActivity implements View.OnClickListener {
    String[] questions = {
            "ผลลัพธ์ของโค้ดต่อไปนี้คืออะไร?\n" + "int number = 20;\nif (number > 5) {\n  System.out.println(\"Greater than 5\");\n}",
            "การวนซ้ำจำนวนครั้งที่แน่นอน ควรใช้คำสั่งใด?:",
            "เติมคำสั่งที่เหมาะสมเพื่อแสดง \"Hello World\" ถ้า x มากกว่า y:\n" + "if (x > y) {\n  // ใส่คำสั่งที่เหมาะสม\n}",
            "คำสั่ง else if ใช้เพื่อตรวจสอบเงื่อนไขใหม่เมื่อเงื่อนไขแรกใน if เป็น:",
            "ลูปต่อไปนี้จะทำงานกี่ครั้ง?:\n" + "for (int i = 0; i < 5; i++) {\n  System.out.println(i);\n}"
    };
    String choice[][] = {{"น้อยกว่า 5", "มากกว่า 5", "เกิดข้อผิดพลาด", "ไม่มีผลลัพธ์"},
            {"for loop", "while loop", "do-while loop", "boolean"},
            {"if, <", "if, >", "else if, <", "else if, >"},
            {"true", "false", "int", "float"},
            {"2", "3", "4", "5"}
    };
    String Answer[] = {
            "มากกว่า 5",
            "for loop",
            "if, >",
            "false",
            "5"
    };
    String nameProfile[] = {"black", "pink", "red", "brown", "green", "orange", "yellow", "cyan", "purple"};
    int picId[] = {R.drawable.black, R.drawable.pink, R.drawable.red, R.drawable.brown, R.drawable.green
            , R.drawable.orange, R.drawable.yellow, R.drawable.cyan, R.drawable.purple};

    TextView questionNumber, question,textUsername;
    ImageButton btnBack;
    ImageView Profile;

    int[] cardId = {R.id.quiz4_card1, R.id.quiz4_card2, R.id.quiz4_card3, R.id.quiz4_card4};
    CardView[] cardViews = new CardView[cardId.length];

    int[] textID = {R.id.textView4_1, R.id.textView4_2, R.id.textView4_3, R.id.textView4_4};
    TextView[] textViews = new TextView[textID.length];

    int imageID[] = {R.id.heart4_1, R.id.heart4_2, R.id.heart4_3};
    ImageView heartImage[] = new ImageView[3];

    int index = 0;
    int life = 2;
    userData user,oldUser;
    // Alert Dialog
    Dialog dialogWin , dialogLose;
    Button btnOkWinner , btnYes , btnNo;
    // icon toast
    int iconAlerttoast[] = {R.drawable.report_check, R.drawable.report_incorrect};
    String name, email, password, progress, profile,oldProgress;
    ManageFile edtProgress;
    final String filename = "User.txt";

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
        String part[] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile4);
        textUsername = findViewById(R.id.quiz4textUsername);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);
        name = part[0];
        email = part[1];
        password = part[2];
        oldProgress = part[3];
        progress = part[3] + "4";
        profile = part[4];
        textUsername.setText(name+"");


        // Dialog Alert When User Win
        dialogWin = new Dialog(Quiz4Activity.this);
        dialogWin.setContentView(R.layout.alertbox_winner);
        dialogWin.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogWin.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialogWin.setCancelable(false);
        btnOkWinner = dialogWin.findViewById(R.id.example_alert_ok);
        btnOkWinner.setOnClickListener(this);

        // Dialog Alert When User Lose
        dialogLose = new Dialog(Quiz4Activity.this);
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

        question = findViewById(R.id.quiz4_question);
        questionNumber = findViewById(R.id.titleQuestion4);
        btnBack = findViewById(R.id.btnquiz4_BackHome);
        btnBack.setOnClickListener(this);
        for (int i = 0; i < imageID.length; i++) {
            heartImage[i] = findViewById(imageID[i]);
        }
        for (int i = 0; i < cardId.length; i++) {
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
        if (id == R.id.btnquiz4_BackHome || id == R.id.example_alert_no) {
            oldUser = new userData(name, email, password, oldProgress, profile);
            status = true;
            Intent launch = new Intent(this, ContentActivity4.class);
            launch.putExtra("user",oldUser);
            startActivity(launch);
        } else if (id == R.id.example_alert_ok) {
            edtProgress = new ManageFile(this, name, email, password, progress, profile, filename);
            edtProgress.UpdateData(name, email, password, profile, false);
            user = new userData(name, email, password, progress, profile);
            status = true;
            Intent launch = new Intent(this, MainActivity.class);
            launch.putExtra("user", user);
            startActivity(launch);
        }

        for (int i = 0; i < cardId.length; i++) {
            if (id == cardId[i]) {
                if (choice[index][i] == Answer[index]) {
                    showToast("Correct!", 0);
                    index++;
                    if (index == questions.length) {
                        status = true;
                        dialogWin.show();
                        break;
                    }
                    question.setText(questions[index]);
                    questionNumber.setText("Question " + (index + 1) + " : ");
                    SetChoice(index);
                    status = true;
                    break;
                }
            }
        }
        if (!status) {
            // toast incorrect
            showToast("Incorrect!", 1);
            if (life >= 0) {
                heartImage[life].setImageResource(R.drawable.broken);
            }
            life--;
            if (life < 0) {
                dialogLose.show();
            }
        }
    }

    // TOAST SUCCESS
    public void showToast(String message, int pic) {
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

    public void SetChoice(int index) {
        for (int i = 0; i < cardId.length; i++) {
            textViews[i].setText(choice[index][i] + "");
        }
    }

    public void ResetQuize() {
        dialogLose.dismiss();
        index = 0;
        life = 2;
        question.setText(questions[0] + "");
        questionNumber.setText("Question " + index + 1 + " : ");
        for (int i = 0; i < cardId.length; i++) {
            textViews[i].setText(choice[0][i] + "");
        }
        for (int i = 0; i < heartImage.length; i++) {
            heartImage[i].setImageResource(R.drawable.heart_1);
        }
    }
}