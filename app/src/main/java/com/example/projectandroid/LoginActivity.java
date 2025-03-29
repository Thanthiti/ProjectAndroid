package com.example.projectandroid;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button btnSignUp;
    TextInputEditText editName,editPass;
    TextView textSignUp;
    TextInputLayout layout_username , layout_password ;
    //ImageView togglePass;
    final String filename = "User.txt";
    boolean valid = false;
    userData user;
    ManageFile readFile;
    ArrayList<String> Alldata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layout_username = findViewById(R.id.login_layout_username);
        layout_password = findViewById(R.id.login_layout_pass);

        layout_username.setHelperTextEnabled(false);
        layout_password.setHelperTextEnabled(false);


        textSignUp = findViewById(R.id.login_text_signup);
        textSignUp.setOnClickListener(this);

        btnSignUp = findViewById(R.id.LoginbtnSignup);
        btnSignUp.setOnClickListener(this);

        editName = findViewById(R.id.LogineditName);
        editPass = findViewById(R.id.LogineditPass);
        //togglePass = findViewById(R.id.LogintogglePassword);
        //togglePass.setOnClickListener(this);

        editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editPass.setSelection(editPass.getText().length());
        //togglePass.setImageResource(R.drawable.visibility);
        editName.setText("");
        editPass.setText("");

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        String name,pass;
        name = editName.getText().toString().trim();
        pass = editPass.getText().toString().trim();

        if (id == R.id.login_text_signup) {
            Intent i = new Intent(this , RegisterActivity.class);
            startActivity(i);
        }

        if(id == R.id.LoginbtnSignup){
            if(checkName(name) && checkPass(pass)) {
//                if(true) {
                readFile = new ManageFile(this,filename);
                Alldata = readFile.readFile();
                Checklogin(name, pass);
//            SendData();
            if (valid) {
                    SendData();
            }
        }
        }
    }

    public void Checklogin(String name , String pass) {


        for (String users : Alldata) {
            String[] part = users.split("\\s+", 5);

            if (part.length < 4) {  // ตรวจสอบให้แน่ใจว่ามีข้อมูลเพียงพอ
                continue;
            }

            if (part[0].equals(name)) {
                if (part[2].equals(pass)) {

                    user = new userData(part[0], part[1], part[2], part[3], part[4]);
                    valid = true;

                    layout_password.setHelperTextEnabled(false);
                    break;
                } else {
                    layout_password.setHelperTextEnabled(true);
                    layout_password.setHelperText("Incorrect username or password. Please try again.");
                    layout_password.setHelperTextColor(ColorStateList.valueOf(Color.RED));
                    return;
                }
            }
        }

        if (!valid) {
            layout_password.setHelperTextEnabled(true);
            layout_password.setHelperText("Incorrect username or password. Please try again.");
            layout_password.setHelperTextColor(ColorStateList.valueOf(Color.RED));
        }
    }

    public void  SendData(){
        Intent launch = new Intent(LoginActivity.this, MainActivity.class);
//        user = new userData("Palm","asd@gmail","12345678","0","black");
        launch.putExtra("user",user);
        startActivity(launch);
    }

    public boolean checkName(String name){
        if(TextUtils.isEmpty(name)){
            layout_username.setHelperTextEnabled(true);
            layout_username.setHelperText("Oops! You forgot to enter a username.");
            layout_username.setHelperTextColor(ColorStateList.valueOf(Color.RED));
            return  false;
        }
        layout_username.setHelperTextEnabled(false);
        return true;
    }
    public boolean checkPass(String pass){
        if(TextUtils.isEmpty(pass)){
            layout_password.setHelperTextEnabled(true);
            layout_password.setHelperText("Oops! You forgot to enter a password.");
            layout_password.setHelperTextColor(ColorStateList.valueOf(Color.RED));
            return  false;
        }
        layout_password.setHelperTextEnabled(false);
        return true;
    }
}