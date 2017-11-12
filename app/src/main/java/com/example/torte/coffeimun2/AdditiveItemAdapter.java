package com.example.torte.coffeimun2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torte.coffeimun2.activity.CoffeeHouseActivity;
import com.example.torte.coffeimun2.model.Additives;
import com.example.torte.coffeimun2.model.Menu;
import com.example.torte.coffeimun2.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by dmitry on 11/11/17.
 */

public class AdditiveItemAdapter  extends ArrayAdapter<AdditiveItem> {

    private ArrayList<View> createdViews = new ArrayList<>();

    private final TextView totalPriceView;
    private int basePrice;
    private AssetManager assets;

    public AdditiveItemAdapter(Context context, ArrayList<AdditiveItem> items, TextView totalPriceView, int basePrice, AssetManager assets){
        super(context, 0, items);
        this.totalPriceView = totalPriceView;
        this.basePrice = basePrice;
        this.assets = assets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        AdditiveItem item = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.additive_item, parent, false);
            createdViews.add(convertView);
        }

        Typeface font = FontHelper.GetFont(assets);

        TextView name = (TextView)convertView.findViewById(R.id.additive_item_name_textView);
        name.setTypeface(font);
        SeekBar countBar = (SeekBar)convertView.findViewById(R.id.additive_item_seekbar);
        TextView count = (TextView)convertView.findViewById(R.id.additive_item_count_textView);
        count.setTypeface(font);

        name.setText(item.name);
        countBar.setProgress(0);
        count.setText("0");

        countBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                count.setText("" + i);
                totalPriceView.setText("" + GetTotalCost() + " ₽");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return convertView;
    }

    private int GetTotalCost() {
        int totalCost = basePrice;
        for (View view : createdViews)
        {
            int count = GetAdditiveViewCount(view);
            totalCost += count * 10;
        }
        return totalCost;
    }

    private static int GetAdditiveViewCount(View view)
    {
        SeekBar bar = view.findViewById(R.id.additive_item_seekbar);
        int count = bar.getProgress();
        return count;
    }

    static  public void WonderfulMagicMethod(Menu menu, Context context, Activity activity, String cafeId)
   {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

       Typeface font = FontHelper.GetFont(activity.getAssets());

        TextView dialogTextContent = (TextView)dialogView.findViewById(R.id.dialog_text);
        dialogTextContent.setTypeface(font);
        dialogTextContent.setText(menu.name);
        ImageView dialogImage = (ImageView)dialogView.findViewById(R.id.dialog_image);
        ImageLoader.AddListener(menu.img, bitmap -> dialogImage.setImageBitmap(bitmap));
        ListView listView = (ListView)dialogView.findViewById(R.id.dialog_listview);
        TextView totalPriceView = dialogView.findViewById(R.id.dialog_total_price);
        totalPriceView.setTypeface(font);
        totalPriceView.setText("" + menu.price + " ₽");

        TextView priceLabel = dialogView.findViewById(R.id.price_label);
        priceLabel.setTypeface(font);

        //-----------------
       ArrayList<AdditiveItem> items = new ArrayList<>();
       for (Additives add : menu.additives.values())
       {
           items.add(new AdditiveItem(add.name));
       }

       AdditiveItemAdapter adapter = new AdditiveItemAdapter(context, items, totalPriceView, menu.price, activity.getAssets());
       listView.setAdapter(adapter);
       //---------------------
        //   dialogImage.setImageResource();
        dialogBuilder.setPositiveButton("Заказать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                Order order = new Order();
                order.id = IdGenerator.GetId();
                order.id_product = menu.id;
                order.id_house = cafeId;
                order.additive = adapter.GetTextOrder(menu.name);

                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                database
                        .child(DataBaseModel.Orders)
                        .child(order.id)
                        .setValue(order);

                Toast.makeText(context, "Order sended", Toast.LENGTH_LONG).show();
                CoffeeHouseActivity ac = (CoffeeHouseActivity)activity;
                ac.Reload();
            }
        });
        dialogBuilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(context, "Order canceled" , Toast.LENGTH_SHORT).show();
            }
        });
       dialogBuilder.setInverseBackgroundForced(true);
       AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private String GetTextOrder(String coffeeName) {
        OrderParser parser = new OrderParser();
        parser.AddCoffeeName(coffeeName);

        for (View view : createdViews)
        {
            TextView addView = view.findViewById(R.id.additive_item_name_textView);

            String add = addView.getText().toString();
            int count = GetAdditiveViewCount(view);
            int cost = count * 10;

            if (count > 0)
            {
                parser.AddAdditive(add, count, cost);
            }
        }
        parser.AddTotal(GetTotalCost());
        String result = parser.Print();
        return result;
    }
}
