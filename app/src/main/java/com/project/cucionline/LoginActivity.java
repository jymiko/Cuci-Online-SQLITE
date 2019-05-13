package com.project.cucionline;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView textRegister;
    Button btnLogin;
    EditText editTextEmail, editTextPassword;

    DatabaseHelper databaseHelper;
    Cursor lihatdata;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
        sessionManagement = new SessionManagement(getApplicationContext());

        editTextEmail = (EditText)findViewById(R.id.txtusername);
        editTextPassword = (EditText)findViewById(R.id.txtpassword);

        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        textRegister = (TextView)findViewById(R.id.txtregister);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class );
                startActivity(i);
                finish();
            }
        });
    }

    private void login(){
        if(databaseHelper.SimpanData(editTextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim())){
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            if(email.trim().length() > 0 && password.trim().length() > 0){
                sessionManagement.createLoginSession(email, password);
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Anda belum terdaftar / username atau password anda salah", Toast.LENGTH_LONG).show();
        }
    }
}
