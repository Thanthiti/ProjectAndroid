package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity implements
        View.OnClickListener {
    TextInputEditText editName,editEmail,editPass;
    ImageView togglePassword;
    Button btnRegister;
    final String filename = "User.txt";
    String Path ;


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

        editName = findViewById(R.id.RegiseditName);
        editEmail = findViewById(R.id.RegiseditEmail);
        editPass = findViewById(R.id.RegiseditPass);

        btnRegister = findViewById(R.id.RegisbtnRegister);
        togglePassword = findViewById(R.id.RegistogglePassword);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.RegistogglePassword){
            if (editPass.getTransformationMethod() instanceof PasswordTransformationMethod) {
                // แสดงรหัสผ่าน
                editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                togglePassword.setImageResource(R.drawable.unvisibility);
            } else {
                // ซ่อนรหัสผ่าน
                editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                togglePassword.setImageResource(R.drawable.visibility);
            }
            editPass.setSelection(editPass.getText().length());
        }

        if(id == R.id.RegisbtnRegister){
            Intent launch = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(launch);
        }
    }
}