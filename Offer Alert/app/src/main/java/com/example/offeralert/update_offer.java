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
import com.google.firebase.database.core.Path;

import java.util.Iterator;

import Model.Admin;

public class update_offer extends AppCompatActivity {
    EditText offer, validity;
    Button submit;
    String email, name, website, address, password, type, contact, off, date;
    DatabaseReference fb;
    String key;
    private static long count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offer);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
        website = intent.getStringExtra("website");
        address = intent.getStringExtra("address");
        password = intent.getStringExtra("password");
        type = intent.getStringExtra("type");
        contact = intent.getStringExtra("contact");

        offer = (EditText) findViewById(R.id.offer);
        validity = (EditText) findViewById(R.id.validity);
        submit = (Button) findViewById(R.id.submit);


        fb = FirebaseDatabase.getInstance().getReference().child("Admin");
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

    public void addOffer(View view) {
        off = offer.getText().toString();
        date = validity.getText().toString();
        Admin admin = new Admin(name, email, password, contact, website, address, type, off, date);

        fb = FirebaseDatabase.getInstance().getReference().child("Admin");
        fb.child(String.valueOf(count+1)).setValue(admin);

        Toast.makeText(this,"Registration Successful!", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Offer added successfully!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();


//            fb = FirebaseDatabase.getInstance().getReference().child("Admin/1");
//            count++;
//            fb.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    fb.child("description").setValue(offer.getText().toString());
//                    fb.child("valid").setValue(validity.getText().toString());
//                    Toast.makeText(getApplicationContext(), "Offer added successfully!", Toast.LENGTH_SHORT).show();
//
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });

    }
}
