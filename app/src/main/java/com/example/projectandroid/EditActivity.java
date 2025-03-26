package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnBack,Profile,editProfile;
    String Name,Email,Password;
    EditText editName,editPass,editEmail;
    String nameProfile [] = {"black","pink","red","brown","green","orange","yellow","cyan","purple"};
    int picId [] = {R.drawable.black,R.drawable.pink,R.drawable.red,R.drawable.brown,R.drawable.green
            ,R.drawable.orange,R.drawable.yellow,R.drawable.cyan,R.drawable.purple};
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

        Profile = findViewById(R.id.EditImageProfile);
        editName = findViewById(R.id.EdittextUsername);
        editPass = findViewById(R.id.EdittextPassword);
        editEmail = findViewById(R.id.EdittextEmail);

        btnBack = findViewById(R.id.edit_btn_back);
        btnBack.setOnClickListener(this);

        int index = Arrays.asList(nameProfile).indexOf(part[4]);
        Profile.setImageResource(picId[index]);

        editName.setText(Name);
        editPass.setText(Password);
        editEmail.setText(Email);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.edit_btn_back) {
            finish();
        }
    }
}