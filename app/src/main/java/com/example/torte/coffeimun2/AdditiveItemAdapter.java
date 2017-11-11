package com.example.torte.coffeimun2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.torte.coffeimun2.model.Additives;
import com.example.torte.coffeimun2.model.Menu;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by dmitry on 11/11/17.
 */

public class AdditiveItemAdapter  extends ArrayAdapter<AdditiveItem> {
    public AdditiveItemAdapter(Context context, ArrayList<AdditiveItem> items){
        super(context, 0, items);
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


        TextView name = (TextView)convertView.findViewById(R.id.additive_item_name_textView);
        SeekBar countBar = (SeekBar)convertView.findViewById(R.id.additive_item_seekbar);
        TextView count = (TextView)convertView.findViewById(R.id.additive_item_count_textView);

        name.setText(item.name);
        countBar.setProgress(0);
        count.setText("0");

        return convertView;
    }


   static  public void WonderfulMagicMethod(Menu menu, Context context, Activity activity)
   {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        TextView dialogTextContent = (TextView)dialogView.findViewById(R.id.dialog_text);
        ImageView dialogImage = (ImageView)dialogView.findViewById(R.id.dialog_image);
        ListView listView = (ListView)dialogView.findViewById(R.id.dialog_listview);

        //-----------------
       ArrayList<AdditiveItem> items = new ArrayList<>();
       for (Additives add : menu.additives.values())
       {
           items.add(new AdditiveItem(add.name));
       }

       AdditiveItemAdapter adapter = new AdditiveItemAdapter(context, items);
       listView.setAdapter(adapter);
       //---------------------

        dialogBuilder.setTitle("Custom Dialog Box");
        //   dialogImage.setImageResource();
        dialogBuilder.setPositiveButton("Заказать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int sum = adapter.GetTotalSum();
                Toast.makeText(context, "OKKKK " + sum , Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(context, "Ты ушел(" , Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private ArrayList<View> createdViews = new ArrayList<>();

    private int GetTotalSum() {
        int sum = 0;
        for (View view : createdViews)
        {
            SeekBar bar = view.findViewById(R.id.additive_item_seekbar);
            int count = bar.getProgress();
            sum += count;
        }
        return sum;
    }
}
