package com.example.ahdiatahsan_182102_c;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText txtUser = findViewById(R.id.txtUsername);
        String user = txtUser.getText().toString();
        EditText txtPass = findViewById(R.id.txtPassword);
        String pass = txtPass.getText().toString();
        Button btnLogin = findViewById(R.id.btnMasuk);
        Button btnCancel = findViewById(R.id.btnBatal);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUser.getText().toString().equals("182102") &&
                        txtPass.getText().toString().equals("MOBILE_C")) {
                    Toast.makeText(getApplicationContext(), " Selamat Datang " +
                                    txtUser.getText().toString() + " Di STMIK DIPANEGARA ",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                }
                else if (txtUser.getText().toString().equals("") ||
                        txtPass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username Atau Password Masih Kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username Atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtUser.setText("");
                txtPass.setText("");
            }
        });
    }
}