package com.example.offeralert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import Model.User;

public class ResisterUserActivity extends AppCompatActivity {
    public Button register;
    public EditText name, contact, email, password;
    String n, c, e, p;
    DatabaseReference fb;
    private static long count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister_user);

        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.reg_button);

        fb = FirebaseDatabase.getInstance().getReference().child("User");
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    count=dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void register(View view) {
        n = name.getText().toString();
        e = email.getText().toString();
        p = password.getText().toString();
        c = contact.getText().toString();

        User user = new User(n,e,p,c);

        fb = FirebaseDatabase.getInstance().getReference().child("User");

        fb.child(String.valueOf(count+1)).setValue(user);

        Toast.makeText(this,"Registration Successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
