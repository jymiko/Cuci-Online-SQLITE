package com.project.cucionline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StandActivity extends AppCompatActivity {

    Button buttonOrder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);

        buttonOrder1 = (Button)findViewById(R.id.btnOrder1);
        buttonOrder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StandActivity.this,OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
