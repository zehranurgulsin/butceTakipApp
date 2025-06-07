package com.example.butcetakip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.butcetakip.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        loginBtn = findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("zehranur") && pass.equals("1234")) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Hatalı Giriş", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
