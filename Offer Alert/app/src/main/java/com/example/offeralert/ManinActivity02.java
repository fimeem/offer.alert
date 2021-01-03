package com.example.offeralert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManinActivity02 extends AppCompatActivity {

    private Button user, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manin02);
        user = (Button)findViewById(R.id.reg_as_user);
        admin = (Button) findViewById(R.id.reg_as_admin);

    }

    public void view3(View view) {
        Intent intent03 = new Intent(ManinActivity02.this, ResisterUserActivity.class);
        startActivity(intent03);
    }

    public void view04(View view) {
        Intent intent04 = new Intent(ManinActivity02.this, ResisterActivity.class);
        startActivity(intent04);
    }
}
