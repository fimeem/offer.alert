package com.example.offeralert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    String e, p;
    Button login;
    DatabaseReference fb;
    boolean valid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_button);
    }

    public void check(View view) {
        if(String.valueOf(email.getText()).isEmpty())
        {
            email.setError("Invalid Email!");
            email.requestFocus();
            email.setText("");
        }
        else if(String.valueOf(password.getText()).isEmpty())
        {
            password.setError("Invalid Password!");
            password.requestFocus();
            password.setText("");
        }
        else
        {
            e=String.valueOf(email.getText());
            p=String.valueOf(password.getText());
            goToHome();
        }

    }

    private void goToHome() {

        fb = FirebaseDatabase.getInstance().getReference().child("User");
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean valid=false;
                Iterator<DataSnapshot> values = dataSnapshot.getChildren().iterator();
                while(values.hasNext())
                {
                    DataSnapshot value = values.next();
                    String em = String.valueOf(value.child("email").getValue());
                    if(em.equals(e))
                    {
                        valid = true;
                        String pass = String.valueOf(value.child("password").getValue());
                        if(pass.equals(p))
                        {
                            user();
                        }
                        else
                        {
                            error();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                error();
            }
        });

        if(!valid)
        {
            fb = FirebaseDatabase.getInstance().getReference().child("Admin");
            fb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    boolean valid=false;
                    Iterator<DataSnapshot> values = dataSnapshot.getChildren().iterator();
                    while(values.hasNext())
                    {
                        DataSnapshot value = values.next();
                        String pass = String.valueOf(value.child("password").getValue());
                        if(pass.equals(p))
                        {
                            admin();
                        }
                        else
                        {
                            error();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    error();
                }
            });
        }
    }

    public void admin()
    {
//        finish();
//        Intent intent = new Intent(this, update_offer.class);
//        intent.putExtra("type", "admin");
//        intent.putExtra("email", e);
//        startActivity(intent);

    }

    public void user()
    {
        finish();
        Intent intent = new Intent(this, OptionsActivity.class);
        intent.putExtra("type", "user");
        intent.putExtra("email", e);
        startActivity(intent);
    }

    public void error()
    {
        email.setError("Invalid Email!");
        email.requestFocus();
        email.setText("");
        password.setError("Invalid Password!");
        password.requestFocus();
        password.setText("");
    }
}
