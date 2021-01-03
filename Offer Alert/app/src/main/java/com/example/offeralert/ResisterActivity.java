package com.example.offeralert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Admin;

public class ResisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public Button regButton;
    public EditText name, contact, email, password, website, address;
    String n, c, e, p, w, a, t;
    DatabaseReference fb;
    private static long count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister);
        regButton = (Button) findViewById(R.id.reg_button);
        name = (EditText) findViewById(R.id.T_name1);
        email = (EditText) findViewById(R.id.T_mail1);
        contact = (EditText) findViewById(R.id.T_number1);
        password = (EditText) findViewById(R.id.password);
        website = (EditText) findViewById(R.id.T_page_link);
        address = (EditText) findViewById(R.id.address);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        t = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void register(View view) {
        n = name.getText().toString();
        e = email.getText().toString();
        p = password.getText().toString();
        c = contact.getText().toString();
        w = website.getText().toString();
        a = address.getText().toString();

//        Admin admin = new Admin(n, e, p, c, w, a, t, "", "");
//
//        fb = FirebaseDatabase.getInstance().getReference().child("Admin");
//        fb.child(String.valueOf(count+1)).setValue(admin);
//
//        Toast.makeText(this,"Registration Successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, update_offer.class);
        intent.putExtra("address", a);
        intent.putExtra("contact", c);
        intent.putExtra("email", e);
        intent.putExtra("name", n);
        intent.putExtra("password", p);
        intent.putExtra("type", t);
        intent.putExtra("website", w);

        startActivity(intent);
        finish();

    }
}
