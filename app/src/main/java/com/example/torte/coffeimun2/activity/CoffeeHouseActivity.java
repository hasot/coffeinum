package com.example.torte.coffeimun2.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torte.coffeimun2.R;
import com.example.torte.coffeimun2.adapter.CoffeHouseAdapter;
import com.example.torte.coffeimun2.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.model.CreateList;

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
            showChangeLangDialog(position);
        };
        CoffeHouseAdapter adapter = new CoffeHouseAdapter(getApplicationContext(), createLists, listener);
        recyclerView.setAdapter(adapter);
    }

    public void showChangeLangDialog(Integer id) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        TextView dialogTextContent = (TextView)dialogView.findViewById(R.id.dialog_text);
        ImageView dialogImage = (ImageView)dialogView.findViewById(R.id.dialog_image);
        dialogBuilder.setTitle("Custom Dialog Box");
        dialogImage.setImageResource(this.image_ids[id]);
        dialogBuilder.setPositiveButton("Заказать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "OKKKK" , Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(), "Отменить" , Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
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
