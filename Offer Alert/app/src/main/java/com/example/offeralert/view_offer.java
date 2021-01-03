package com.example.offeralert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class view_offer extends AppCompatActivity {
    ListView list;
    ArrayAdapter<String> itemsAdapter;
    private static List<String> items;
    DatabaseReference fb;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_offer);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        list = (ListView) findViewById(R.id.list);
        items = new ArrayList<String>();

        getOffers();

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.RED);

                // Generate ListView Item using TextView
                return view;
            }
        };
        list.setAdapter(itemsAdapter);

        //getClickListner();
    }

    private void getOffers() {
        fb = FirebaseDatabase.getInstance().getReference().child("Admin");
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> values = dataSnapshot.getChildren().iterator();
                while(values.hasNext()) {

                    DataSnapshot value = values.next();

                    String ty=value.child("type").getValue().toString();
                    if(ty.equals(type))
                    {
                        String description = value.child("description").getValue().toString();
                        String validity = value.child("valid").getValue().toString();
                        String store = value.child("name").getValue().toString();

                        LocalDate date;
                        Month mo;
                        String d="";
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            date = LocalDate.now();
                            d=date.toString();
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1=null , date2=null;
                        try {
                            date1 = sdf.parse(validity);
                            date2 = sdf.parse(d);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (date1.after(date2)) {
                            items.add(description +" by "+ store + " Validity: "+ validity);
                            itemsAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
