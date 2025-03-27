package com.example.projectandroid;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity implements
        View.OnClickListener{
    TextInputEditText editName,editEmail,editPass;
    TextInputLayout layout_pass , layout_username;
    //ImageView togglePassword;
    TextView textSignIn;
    Button btnRegister;
    final String filename = "User.txt";
    String Path ;
    ArrayList<String> Username = new ArrayList<>();
    boolean Statusfile = false;
    String PicProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
//    int Pic [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
//    ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        File file = new File(getFilesDir(),filename);

        //file.delete();
        if(file.exists()){
            Toast.makeText(this, "file", Toast.LENGTH_SHORT).show(); // have file
            Statusfile = true;
        }
        textSignIn = findViewById(R.id.regis_btn_signin);
        textSignIn.setOnClickListener(this);

        layout_pass = findViewById(R.id.regis_layout_pass);
        layout_pass.setHelperTextEnabled(false);
        layout_username = findViewById(R.id.regis_layout_username);
        layout_username.setHelperTextEnabled(false);

        editName = findViewById(R.id.RegiseditName);
        editEmail = findViewById(R.id.RegiseditEmail);
        editPass = findViewById(R.id.RegiseditPass);

        btnRegister = findViewById(R.id.RegisbtnRegister);
        //togglePassword = findViewById(R.id.RegistogglePassword);
        //togglePassword.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editPass.setSelection(editPass.getText().length());
       // togglePassword.setImageResource(R.drawable.unvisibility);

        Path = "/data/data/"+getPackageName() +"/files/";

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.regis_btn_signin) {
            Intent i = new Intent(this , LoginActivity.class) ;
            startActivity(i);
        }
       /* if(id == R.id.RegistogglePassword){
            if (editPass.getTransformationMethod() instanceof PasswordTransformationMethod) {
                editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                togglePassword.setImageResource(R.drawable.visibility);
            } else {
                editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                togglePassword.setImageResource(R.drawable.unvisibility);
            }
            editPass.setSelection(editPass.getText().length());
        }*/
        if (id == R.id.RegisbtnRegister) {

            String name,email,pass;
            name = editName.getText().toString().trim();
            email = editEmail.getText().toString().trim().toLowerCase();
            pass = editPass.getText().toString().trim();
            boolean status = false;
            if(checkName(name) && checkEmail(email) && checkPass(pass) ){
                if(Statusfile) {
                    readFile();
                    for (String user: Username) {
                        if (user.equals(name)) {
                            layout_username.setHelperTextEnabled(true);
                            layout_username.setHelperText("Username already taken. Try another.");
                            layout_username.setHelperTextColor(ColorStateList.valueOf(Color.RED));;
                            status = true;
                        }
                    }
                }
                if(!status){
                    Toast.makeText(this, "สำเร็จ", Toast.LENGTH_SHORT).show();
                        saveFile(name,email,pass);
                        Intent launch = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(launch);
                }
            }
        }
    }
    public boolean checkName(String Name){
        if(TextUtils.isEmpty(Name)){
            layout_username.setHelperTextEnabled(true);
            layout_username.setHelperText("Oops! You forgot to enter a username.");
            layout_username.setHelperTextColor(ColorStateList.valueOf(Color.RED));;
            return false;
        }
        if (Name.length() < 8 || Name.length() > 20) {
            layout_username.setHelperTextEnabled(true);
            layout_username.setHelperText("Must be between 8-20 characters.");
            layout_username.setHelperTextColor(ColorStateList.valueOf(Color.RED));;
            return false;
        }
        layout_username.setHelperTextEnabled(false);
        return true;
    }
    public boolean checkEmail(String Email){

        if (TextUtils.isEmpty(Email)) {
            editEmail.setError("กรุณากรอกอีเมล");
            return false;
        }
        if (!Email.contains("@")) {  // ตรวจสอบว่าอีเมลมีเครื่องหมาย "@" หรือไม่
            editEmail.setError("รูปแบบอีเมลไม่ถูกต้อง");
            return false;
        }
        boolean isvalid = false;
        String Emailsplit []= Email.split("@");
        String formatEmail [] = {"gmail.com","email.com","email.kmutnb.ac.th","hotmail.com"};
        for (String domain: formatEmail) {
            if(domain.equals(Emailsplit[1])){
                isvalid = true;
                break;
            }
        }
        if(!isvalid){
            editEmail.setError("รูปแบบอีเมลไม่ถูกต้อง");
            return  false;
        }

        return true;
    }
    public boolean checkPass(String Pass){
        if(TextUtils.isEmpty(Pass)) {
            layout_pass.setHelperTextEnabled(true);
            layout_pass.setHelperText("Oops! You forgot to enter a password.");
            layout_pass.setHelperTextColor(ColorStateList.valueOf(Color.RED));;
            return false;
        }
        if (Pass.length() < 8 || Pass.length() > 20) {
            layout_pass.setHelperTextEnabled(true);
            layout_pass.setHelperText("Must be between 8-20 characters");
            layout_pass.setHelperTextColor(ColorStateList.valueOf(Color.RED));;
            return false;
        }
        return true;
    }
    public void saveFile(String Name,String Email, String Pass){
        try {
            FileOutputStream fout = openFileOutput(filename, MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fout);
            Random rand = new Random();
            writer.write(Name + " " + Email + " " + Pass + " 0 "+PicProfile[rand.nextInt(PicProfile.length-1)] + "\n");

            writer.flush();
            writer.close();
            Toast.makeText(this, "บันทึกเรียบร้อย", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show();
        }
        }
    public  void readFile(){
                  try {
                FileInputStream fin = openFileInput(filename);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] part = line.split("\\s+", 5);
                    Username.add(part[0]);
                }
                reader.close();
            } catch (IOException e) {
                Toast.makeText(this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show();
            }
        }
        }


