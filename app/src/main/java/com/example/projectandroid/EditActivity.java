package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnBack,Profile,editProfile;
    String Name,Email,Password,profile;
    EditText editName,editPass,editEmail;
    Button btnUpdate;
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};

    ManageFile editData;
    userData updateUser;
    int Progress;
    final String filename = "User.txt";

    Boolean status = false;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();
        userData user = (userData) i.getSerializableExtra("user");

        String [] part = user.toString().split(" ");

        Name = part[0];
        Email = part[1];
        Password = part[2];
        Progress = Integer.parseInt(part[3]);
        profile = part[4];

        Profile = findViewById(R.id.EditImageProfile);
        editName = findViewById(R.id.EdittextUsername);
        editPass = findViewById(R.id.EdittextPassword);
        editEmail = findViewById(R.id.EdittextEmail);

        btnBack = findViewById(R.id.edit_btn_back);
        btnBack.setOnClickListener(this);
        btnUpdate = findViewById(R.id.EditbtnUpdate);
        btnUpdate.setOnClickListener(this);

        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);

        editName.setText(Name);
        editPass.setText(Password);
        editEmail.setText(Email);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String textName,textEmail,textPass;
        textName = editName.getText().toString();
        textEmail = editEmail.getText().toString();
        textPass = editPass.getText().toString();
        if (id == R.id.edit_btn_back) {

            Intent i = new Intent(EditActivity.this,MainActivity.class);
            if(status)updateUser = new userData(textName,textEmail,textPass,Progress,profile);
            else updateUser = new userData(Name,Email,Password,Progress,profile);
            i.putExtra("user",updateUser);
            startActivity(i);

        }
        else if(id == R.id.EditbtnUpdate){

            if(textName.equals(Name) && textEmail.equals(Email) && textPass.equals(Password)){
                Toast.makeText(this, "ข้อมูลไม่มีการเปลี่ยนแปลง", Toast.LENGTH_SHORT).show();
            }else {
                if(checkName(textName)&&checkEmail(textEmail)&& checkPass(textPass)){
                    Toast.makeText(this, "เปลี่ยน", Toast.LENGTH_SHORT).show();
//                  Old Data
                    editData = new ManageFile(this,Name,Email,Password,Progress,profile,filename);
//                  New Data
                    editData.UpdateData(textName,textEmail,textPass);
                    status = true;
                }
                else{
                    Toast.makeText(this, "การกรอกข้อมูลไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
    public boolean checkName(String Name){
        if(TextUtils.isEmpty(Name)){
            editName.setError("กรุณากรอกชื่อผู้ใช้");
            return false;
        }
        if (Name.length() < 8 || Name.length() > 12) {
            editName.setError("กรุณากรอกชื่อผู้ใช้ 8 - 12 ตัว");
            return false;
        }
        return true;
    }
    public boolean checkEmail(String Email){

        if (TextUtils.isEmpty(Email)) {
            editEmail.setError("กรุณากรอกอีเมล");
            return false;
        }
        if (!Email.contains("@")) {
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
            editPass.setError("กรุณากรอกรหัสผ่าน");
            return false;
        }
        if (Pass.length() < 8 || Pass.length() > 12) {
            editPass.setError("กรอกรหัสผ่านอย่างน้อย 8 - 12 ตัว");
            return false;
        }
        return true;
    }
}