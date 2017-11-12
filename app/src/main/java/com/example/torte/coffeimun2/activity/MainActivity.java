package com.example.torte.coffeimun2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.torte.coffeimun2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       Intent intent = new Intent(this, CoffeeHouseActivity.class);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
