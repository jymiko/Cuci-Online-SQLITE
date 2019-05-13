package com.project.cucionline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    SessionManagement sessionManagement;
    Button cariTempat, cekStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManagement = new SessionManagement(getApplicationContext());
        sessionManagement.checkLogin();
        HashMap<String,String> user = sessionManagement.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String email = user.get(SessionManagement.KEY_EMAIL);

        cariTempat = (Button)findViewById(R.id.btncaritempat);
        cariTempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StandActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cekStatus = (Button)findViewById(R.id.btncekstatus);
    }
}
