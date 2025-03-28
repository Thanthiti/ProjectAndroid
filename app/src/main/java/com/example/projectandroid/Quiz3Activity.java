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

public class Quiz3Activity extends AppCompatActivity implements View.OnClickListener {
    CardView card1;
    String[] questions = {
            "ตัวดำเนินการ (Operators) ใช้เพื่อ:",
            "คูณ 10 ด้วย 5 และพิมพ์ผลลัพธ์ออกมา:  \n" +
                    "System.out.println(10 __ 5);",
            "ใช้ตัวดำเนินการที่ถูกต้องเพื่อเพิ่มค่าของตัวแปร x ขึ้นทีละ 1:",
            "ใช้ตัวดำเนินการเพื่อเพิ่มค่า 5 ให้กับตัวแปร x:\n" +
                    "int x = 10;\n" +
                    "x __ 5;",
            "ตัวดำเนินการตรรกศาสตร์ && (AND) จะคืนค่า true ในกรณีใด?:"
    };
    String choice[][] = {{"สร้างค่าคงที่และค่าตัวแปร", "ดำเนินการกับตัวแปรและค่า", "สร้างออบเจ็กต์และคลาส", "เข้าถึงคอมเมนต์เพื่อแสดงบนหน้าจอ"},
            {"+", "-", "*", "/"},
            {"++", "--", "**", "//"},
            {"=-", "-=", "=+", "+="},
            {"เมื่อเงื่อนไขอย่างน้อยหนึ่งเป็น true", "เมื่อทุกเงื่อนไขเป็น true", "เมื่อเงื่อนไขอย่างน้อยหนึ่งเป็น false", "เมื่อเงื่อนไขอย่างน้อยหนึ่งเป็น false"}
    };
    String Answer[] = {
            "ดำเนินการกับตัวแปรและค่า",
            "*",
            "++",
            "+=",
            "เมื่อทุกเงื่อนไขเป็น true"
    };
    String nameProfile[] = {"black", "pink", "red", "brown", "green", "orange", "yellow", "cyan", "purple"};
    int picId[] = {R.drawable.black, R.drawable.pink, R.drawable.red, R.drawable.brown, R.drawable.green
            , R.drawable.orange, R.drawable.yellow, R.drawable.cyan, R.drawable.purple};

    TextView questionNumber, question,textUsername;
    ImageButton btnBack;
    ImageView Profile;

    int[] cardId = {R.id.quiz3_card1, R.id.quiz3_card2, R.id.quiz3_card3, R.id.quiz3_card4};
    CardView[] cardViews = new CardView[cardId.length];

    int[] textID = {R.id.textView3_1, R.id.textView3_2, R.id.textView3_3, R.id.textView3_4};
    TextView[] textViews = new TextView[textID.length];

    int imageID[] = {R.id.heart3_1, R.id.heart3_2, R.id.heart3_3};
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
        setContentView(R.layout.activity_quiz3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent launch = getIntent();
        user = (userData) launch.getSerializableExtra("user");
        String part[] = user.toString().split(" ");

        Profile = findViewById(R.id.imgProfile3);
        textUsername = findViewById(R.id.quiz3textUsername);
        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);
        name = part[0];
        email = part[1];
        password = part[2];
        oldProgress = part[3];
        progress = part[3] + "3";
        profile = part[4];
        textUsername.setText(name+"");

        // Dialog Alert When User Win
        dialogWin = new Dialog(Quiz3Activity.this);
        dialogWin.setContentView(R.layout.alertbox_winner);
        dialogWin.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogWin.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialogWin.setCancelable(false);
        btnOkWinner = dialogWin.findViewById(R.id.example_alert_ok);
        btnOkWinner.setOnClickListener(this);

        // Dialog Alert When User Lose
        dialogLose = new Dialog(Quiz3Activity.this);
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
        question = findViewById(R.id.quiz3_question);
        questionNumber = findViewById(R.id.titleQuestion3);
        btnBack = findViewById(R.id.btnquiz3_BackHome);
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
        if (id == R.id.btnquiz3_BackHome || id == R.id.example_alert_no) {
            oldUser = new userData(name, email, password, oldProgress, profile);
            status = true;
            Intent launch = new Intent(this,ContentActivity3.class);
            launch.putExtra("user",oldUser);
            startActivity(launch);
        } else if (id == R.id.example_alert_ok) {
            edtProgress = new ManageFile(this, name, email, password, progress, profile, filename);
            edtProgress.UpdateData(name, email, password, profile, false);
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