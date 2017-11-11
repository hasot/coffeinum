package com.example.torte.coffeimun2.activity.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.activity.adapter.CoffeHouseAdapter;
import com.example.torte.coffeimun2.activity.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.activity.model.CreateList;

import java.util.ArrayList;

public class CoffeeHouseActivity extends AppCompatActivity {

    private Context context;
    private final String image_titles[] = {
            "Капучино",
            "Латте",
            "Печенька",
            "Твоя душа",
    };

    private final Integer image_ids[] = {
            R.drawable.coffee,
            R.drawable.coffee,
            R.drawable.coffee,
            R.drawable.coffee,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_house);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreateList> createLists = prepareData();
        RecyclerViewClickListener listener = (view, position) -> {
            Toast.makeText(getApplicationContext(), "Position " + position , Toast.LENGTH_SHORT).show();
        };
        CoffeHouseAdapter adapter = new CoffeHouseAdapter(getApplicationContext(), createLists, listener);
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theimage = new ArrayList<>();
        for(int i = 0; i< image_titles.length; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            theimage.add(createList);
        }
        return theimage;
    }
}
