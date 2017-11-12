package com.example.torte.coffeimun2.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torte.coffeimun2.CheckAdapter;
import com.example.torte.coffeimun2.DataBaseModel;
import com.example.torte.coffeimun2.ImageLoader;
import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.adapter.CoffeHouseAdapter;
import com.example.torte.coffeimun2.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.model.CoffeHouseModel;
import com.example.torte.coffeimun2.model.Menu;
import com.example.torte.coffeimun2.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.torte.coffeimun2.AdditiveItemAdapter.WonderfulMagicMethod;

public class CoffeeHouseActivity extends AppCompatActivity {

    private Context context;
    private ImageView headImage;
    private TextView nameText;

    private String currentCafeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_house);
        headImage = (ImageView)findViewById(R.id.head_image);
        nameText = (TextView) findViewById(R.id.name_text);
        Intent intent = getIntent();
        String cafeId =  intent.getStringExtra(DataBaseModel.cafeIdFromMapsActivity);
        currentCafeId = cafeId;
        LoadMenu(cafeId);
    }



    private void LoadMarkers(ArrayList<Menu> list, String cafeId) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database
                .child(DataBaseModel.Orders)
                .orderByChild("id_house")
                .equalTo(cafeId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Order order = snapshot.getValue(Order.class);
                            LoadOrder(order);
                            return;
                        }
                        initMenuRecyclerView(list, cafeId);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//        LoadMenu(id);
    }

    public void Reload()
    {
        LoadMenu(currentCafeId);
    }


    private void LoadMenu(String id) {
        ArrayList<Menu> list = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase
                .child(DataBaseModel.COFFEE_HOUSE)
                .orderByChild("id")
                .equalTo(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        CoffeHouseModel coffeeHouse = null;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            coffeeHouse = snapshot.getValue(CoffeHouseModel.class);
                            ImageLoader.AddListener(coffeeHouse.head_img, bitmap -> headImage.setImageBitmap(bitmap));
                            nameText.setText(coffeeHouse.name);
                            for (String key: coffeeHouse.menu.keySet()) {
                                Log.d("key : " , key);
                                Log.d("value : " ,"" + coffeeHouse.menu.get(key).price);
                                list.add(coffeeHouse.menu.get(key));
                            }
                            LoadMarkers(list, id);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error, when load markers" , Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void LoadOrder(Order order) {
        Toast.makeText(getApplicationContext(), "Yeeeah!", Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < 5; ++i)
            tokens.add(order.additive);
        CheckAdapter adapter = new CheckAdapter(getApplicationContext(), tokens);
        recyclerView.setAdapter(adapter);
    }

    private void initMenuRecyclerView(ArrayList<Menu> list, String id) {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = (view, menu) -> {
            WonderfulMagicMethod(menu, getApplicationContext(), this, id);
        };
        CoffeHouseAdapter adapter = new CoffeHouseAdapter(getApplicationContext(), list, listener);
        recyclerView.setAdapter(adapter);
    }
}
