package com.project.cucionline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    View rootView;
    EditText nama, alamat, notelp, email, password, confirmpass, motor;
    Button register;

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        rootView = (View)findViewById(R.id.rootViewRegister);

        nama = (EditText)findViewById(R.id.edtnama);
        alamat = (EditText)findViewById(R.id.edtalamat);
        notelp = (EditText)findViewById(R.id.edtnotelp);
        email = (EditText)findViewById(R.id.edtemail);
        password = (EditText)findViewById(R.id.edtpass);
        confirmpass = (EditText)findViewById(R.id.edtconfirmpass);
        motor = (EditText)findViewById(R.id.edtmotor);
        register = (Button)findViewById(R.id.btnregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPassword();
                postDataSQLite();
            }
        });
    }

    private void postDataSQLite(){
        String nama_user = nama.getText().toString();
        String alamat_user = alamat.getText().toString();
        String notelp_user = notelp.getText().toString();
        String email_user = email.getText().toString();
        String pass_user = password.getText().toString();
        String jenis_motor = motor.getText().toString();

        if(!TextUtils.isEmpty(nama_user)
            && !TextUtils.isEmpty(alamat_user)
            && !TextUtils.isEmpty(notelp_user)
            && !TextUtils.isEmpty(email_user)
            && !TextUtils.isEmpty(pass_user)
            && !TextUtils.isEmpty(jenis_motor)){

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseLogin.UserEntry.COLUMN_NAMA_USER, nama_user);
            contentValues.put(DatabaseLogin.UserEntry.COLUMN_ALAMAT_USER, alamat_user);
            contentValues.put(DatabaseLogin.UserEntry.COLUMN_NOTELP_USER, notelp_user);
            contentValues.put(DatabaseLogin.UserEntry.COLUMN_EMAIL_USER, email_user);
            contentValues.put(DatabaseLogin.UserEntry.COLUMN_PASSWORD_USER, pass_user);
            contentValues.put(DatabaseLogin.UserEntry.COLUMN_MOTOR, jenis_motor);

            long result_register = sqLiteDatabase.insert(
                    DatabaseLogin.UserEntry.TABLE_NAME,
                    null,
                    contentValues
            );

            if(result_register > 0){
                Toast.makeText(this, "Registrasi sukses", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Registrasi gagal", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Silahkan lengkapi form registrasi anda", Toast.LENGTH_LONG).show();
        }


    }

    private void checkPassword(){
        String value1 = password.getText().toString().trim();
        String value2 = confirmpass.getText().toString().trim();

        if(!value1.contentEquals(value2)){
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
        }
    }
}
