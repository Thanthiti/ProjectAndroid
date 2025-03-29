package com.example.projectandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    int linearID []  = {R.id.home_menu_data,R.id.home_menu_variables,R.id.home_menu_operator,R.id.home_menu_control,
    R.id.home_menu_function,R.id.home_menu_edit};
    LinearLayout linears [] = new LinearLayout[linearID.length];

    ConstraintLayout reportExp;
    ImageView logout ,ImageProfile;
    TextView textExp,textUsername;
    String Name,Profile,Progress;
    userData Edituser;
    Dialog dialog;
    Button btnDiaCancel , btnDiaConfirm;

    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
        int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
    ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Dialog Alert
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.alertbox_logout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        dialog.setCancelable(false);

        btnDiaCancel = dialog.findViewById(R.id.btnAlertCancel);
        btnDiaConfirm = dialog.findViewById(R.id.btnAlertConfirm);
        btnDiaCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnDiaConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(MainActivity.this , LoginActivity.class);
               startActivity(i);
            }
        });

        Intent launch = getIntent();
        userData user = (userData) launch.getSerializableExtra("user");
        String part [] = user.toString().split(" ");

        Name = part[0];
        Progress = part[3];
        Profile = part[4];

        Edituser = new userData(part[0],part[1],part[2],Progress,part[4]);

        progressBar = findViewById(R.id.home_progressbar);
        reportExp = findViewById(R.id.home_report_exp);
        reportExp.setOnClickListener(this);
        textExp = findViewById(R.id.home_textExp);

        for(int i = 0 ; i < linearID.length;i++){
            linears[i] = findViewById(linearID[i]);
            linears[i].setOnClickListener(this);
        }
        logout = findViewById(R.id.home_logout);
        logout.setOnClickListener(this);
        ImageProfile = findViewById(R.id.EditImageProfile);
        textUsername = findViewById(R.id.HomeTextUsername);

        int index = Arrays.asList(nameProfile).indexOf(Profile);
        ImageProfile.setImageResource(picId[index]);

        textUsername.setText(part[0]);

//        ProGress Bar
        textExp.setText("Your experience is : " + Progress +" %");
//        progressBar.incrementProgressBy(Progress);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Class classes [] = {ContentActivity1.class, ContentActivity2.class, ContentActivity3.class,
        ContentActivity4.class, ContentActivity5.class, EditActivity.class};
        for(int i = 0 ; i < linearID.length;i++){
            if (id == linearID[i]){
                Intent launch = new Intent(this,classes[i]);
                launch.putExtra("user",Edituser);
                startActivity(launch);
            }
        }

        if (view.getId() == R.id.home_menu_edit) {
            Intent i = new Intent(this , EditActivity.class);
            i.putExtra("user",Edituser);
            startActivity(i);
        } else if (view.getId() == R.id.home_report_exp) {
            Intent i = new Intent(this , ReportActivity.class );
            startActivity(i);
        } else if (view.getId() == R.id.home_logout) {
            dialog.show();
        }
   }

}