package com.example.offeralert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity {
    Button food, store, travel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        food = (Button) findViewById(R.id.food_button);
        store = (Button) findViewById(R.id.store_button);
        travel = (Button) findViewById(R.id.travel_button);
    }

    public void goToFood(View view) {
        Intent intent = new Intent(this, view_offer.class);
        intent.putExtra("type", "Food");
        startActivity(intent);
    }

    public void goToStore(View view) {
        Intent intent = new Intent(this, view_offer.class);
        intent.putExtra("type", "Store");
        startActivity(intent);
    }

    public void goToTravel(View view) {
        Intent intent = new Intent(this, view_offer.class);
        intent.putExtra("type", "Travel");
        startActivity(intent);
    }
}
