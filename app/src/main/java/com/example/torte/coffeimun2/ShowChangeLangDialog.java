package com.example.torte.coffeimun2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torte.coffeimun2.model.Menu;

/**
 * Created by dmitry on 11/11/17.
 */

public class ShowChangeLangDialog {

   static  public void showChangeLangDialog(Menu menu, Context context, Activity activity) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        TextView dialogTextContent = (TextView)dialogView.findViewById(R.id.dialog_text);
        ImageView dialogImage = (ImageView)dialogView.findViewById(R.id.dialog_image);
        dialogBuilder.setTitle("Custom Dialog Box");
        //   dialogImage.setImageResource();
        dialogBuilder.setPositiveButton("Заказать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(context, "OKKKK" , Toast.LENGTH_SHORT).show();
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
}
