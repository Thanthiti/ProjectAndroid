package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {
    Button btnSignUp, btnRegister;
    TextInputEditText editName,editPass;
    ImageView togglePass;
    final String filename = "User.txt";
    boolean valid = false;
    userData user;

    String PicProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    //    int Pic [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
//    ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};
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
        btnRegister = findViewById(R.id.LoginbtnRegister);
        btnSignUp = findViewById(R.id.LoginbtnSignup);
        btnSignUp.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        editName = findViewById(R.id.LogineditName);
        editPass = findViewById(R.id.LogineditPass);
        togglePass = findViewById(R.id.LogintogglePassword);
        togglePass.setOnClickListener(this);

        editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editPass.setSelection(editPass.getText().length());
        togglePass.setImageResource(R.drawable.visibility);
        editName.setText("");
        editPass.setText("");

    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        String name,pass;
        name = editName.getText().toString().trim();
        pass = editPass.getText().toString().trim();

        if(id == R.id.LogintogglePassword){
            if (editPass.getTransformationMethod() instanceof PasswordTransformationMethod) {
                editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                togglePass.setImageResource(R.drawable.unvisibility);
            } else {
                editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                togglePass.setImageResource(R.drawable.visibility);
            }
            editPass.setSelection(editPass.getText().length());
        }

        if(id == R.id.LoginbtnSignup){
            if(checkName(name) && checkPass(pass)) {
                readFile(name, pass);
                if (valid) {
                    SendData();
                }
            }
        }
        else if(id == R.id.LoginbtnRegister){
            Intent launch = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(launch);
        }
    }

    public  void readFile(String name, String pass){
        try {
            FileInputStream fin = openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line;

            while ((line = reader.readLine()) != null){
                String [] part  = line.split("\\s+",5);
                if(part[0].equals(name)){
                    if(part[2].equals(pass)){
                        int Progress = Integer.parseInt(part[3]);
                        user = new userData(part[0],part[1],part[2],Progress,part[4]);
                        valid = true;
                    }
                    else {
                        Toast.makeText(this, "รหัสผ่านไม่ถูกต้องหริอชื่อผู้ใช้ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                        editPass.setError("รหัสผ่านไม่ถูกต้อง");
                        editName.setError("ชื่อผู้ใช้ไม่ถูกต้อง");
                    }
                }
            }
            reader.close();
        }catch (IOException e){
            Toast.makeText(this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show();
        }
    }
    public void  SendData(){
        Intent launch = new Intent(LoginActivity.this, MainActivity.class);
////        user = new userData("Palm","asd@gmail","12345678",0,"black");
        launch.putExtra("user",user);

        startActivity(launch);
    }

    public boolean checkName(String name){
        if(TextUtils.isEmpty(name)){
            editName.setError("กรุณากรอกชื่อผู้ใข้");
            return  false;
        }
        return true;
    }
    public boolean checkPass(String pass){
        if(TextUtils.isEmpty(pass)){
            editPass.setError("กรุณากรอกรหัสผ่าน");
            return  false;
        }
        return true;
    }
}