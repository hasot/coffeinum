package com.example.torte.coffeimun2.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torte.coffeimun2.DataBaseModel;
import com.example.torte.coffeimun2.ImageLoader;
import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.TorteMarker;
import com.example.torte.coffeimun2.adapter.CoffeHouseAdapter;
import com.example.torte.coffeimun2.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.model.CoffeHouseModel;
import com.example.torte.coffeimun2.model.CreateList;
import com.example.torte.coffeimun2.model.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.torte.coffeimun2.ShowChangeLangDialog.showChangeLangDialog;

public class CoffeeHouseActivity extends AppCompatActivity {

    private Context context;
    private ImageView headImage;
    private TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_house);
        headImage = (ImageView)findViewById(R.id.head_image);
        nameText = (TextView) findViewById(R.id.name_text);
        Intent intent = getIntent();
        String cafeId =  intent.getStringExtra(DataBaseModel.cafeIdFromMapsActivity);
        LoadMarkers("54321");

    }



    private void LoadMarkers(String id)

    {    ArrayList<Menu> list = new ArrayList<>();
        ArrayList<String> additives = new ArrayList<>();;
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
                                Log.d("value : " , coffeeHouse.menu.get(key).name);
                                list.add(coffeeHouse.menu.get(key));
                            }
                            recyclesView(list);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Error, when load markers" , Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void recyclesView(ArrayList<Menu> list) {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = (view, menu) -> {
            showChangeLangDialog(menu, getApplicationContext(), this);
        };
        CoffeHouseAdapter adapter = new CoffeHouseAdapter(getApplicationContext(), list, listener);
        recyclerView.setAdapter(adapter);
    }
}
