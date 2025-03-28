package com.example.projectandroid;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    String textName,textEmail,textPass;
    // Dialog
    Dialog dialog;
    Button btnAlertCancel , btnAlertConfirm;
    int btnID [] = {R.id.EditimagePurple,R.id.EditimageBlack,R.id.EditimageRed,R.id.EditimageBrown,R.id.EditimageGreen
            ,R.id.EditimageOrange,R.id.EditimageYellow,R.id.EditimageCyan,R.id.EditimagePink};
    ImageButton btns[] = new ImageButton[btnID.length];

    String nameProfile [] = {"purple","black","red","brown","green","orange","yellow","cyan","pink"};
    int picId [] = {R.drawable.purple,R.drawable.black,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.pink};

    ManageFile editData;
    userData updateUser;
    int Progress;
    final String filename = "User.txt";
    String textProfile = "";
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

           // Dialog Alert
           dialog = new Dialog(EditActivity.this);
           dialog.setContentView(R.layout.alertbox_update);
           dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
           dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
           dialog.setCancelable(false);

           btnAlertCancel = dialog.findViewById(R.id.edit_alert_cancel);
           btnAlertConfirm = dialog.findViewById(R.id.edit_alert_confirm);
           btnAlertCancel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   dialog.dismiss();
               }
           });
           btnAlertConfirm.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   upDate(textName , textEmail , textPass);
               }
           });
           //

        Intent launch = getIntent();
        userData user = (userData) launch.getSerializableExtra("user");

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

        for(int i = 0 ; i < btnID.length;i++){
            btns[i] = findViewById(btnID[i]);
            btns[i].setOnClickListener(this);

        }

        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);
        textProfile = nameProfile[index];

        editName.setText(Name);
        editPass.setText(Password);
        editEmail.setText(Email);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();


        textName = editName.getText().toString();
        textEmail = editEmail.getText().toString();
        textPass = editPass.getText().toString();
        for(int i = 0 ; i < btns.length;i++){
            if(id == btnID[i]){
                textProfile = nameProfile[i];
                Profile.setImageResource(picId[i]);
                break;
            }

        if (id == R.id.edit_btn_back) {

            Intent launch = new Intent(EditActivity.this,MainActivity.class);
            if(status)updateUser = new userData(textName,textEmail,textPass,Progress,textProfile);
            else updateUser = new userData(Name,Email,Password,Progress,profile);
            launch.putExtra("user",updateUser);
            startActivity(launch);

        }
        else if(id == R.id.EditbtnUpdate){

            if(checkName(textName)&&checkEmail(textEmail)&& checkPass(textPass)) {
                dialog.show();
            }
        }
        }
    }

    public void upDate(String textName , String textEmail , String textPass) {
        if(textName.equals(Name) && textEmail.equals(Email) && textPass.equals(Password) && profile.equals(textProfile)){
            Toast.makeText(this, "ข้อมูลไม่มีการเปลี่ยนแปลง", Toast.LENGTH_SHORT).show();
        }
        else {
            if(checkName(textName)&&checkEmail(textEmail)&& checkPass(textPass)){
//                  Old Data
                editData = new ManageFile(this,Name,Email,Password,Progress,profile,filename);
//                  New Data
                Boolean valid = editData.UpdateData(textName,textEmail,textPass,textProfile);
                dialog.dismiss();
                System.out.println("Status " + valid);
                if(valid){
                    status = true;
                }else {
                    editName.setError("ชื่อผู้ใชซ้ำ");
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

        if (Email.indexOf("@") == -1 || Email.indexOf("@") != Email.lastIndexOf("@")) {
            editEmail.setError("รูปแบบอีเมลไม่ถูกต้อง");
            return false;
        }

        String Emailsplit[] = Email.split("@");

        if (Emailsplit.length != 2 || Emailsplit[1].isEmpty()) {
            editEmail.setError("รูปแบบอีเมลไม่ถูกต้อง");
            return false;
        }

        String formatEmail[] = {"gmail.com", "email.com", "email.kmutnb.ac.th", "hotmail.com"};
        boolean isvalid = false;

        for (String domain : formatEmail) {
            if (domain.equals(Emailsplit[1])) {
                isvalid = true;
                break;
            }
        }

        if (!isvalid) {
            editEmail.setError("โดเมนอีเมลไม่ถูกต้อง");
            return false;
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