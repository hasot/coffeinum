package com.example.torte.coffeimun2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by torte on 12.11.2017.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder>{

    private ArrayList<String> tokens;
    private AssetManager assets;

    public CheckAdapter(ArrayList<String> tokens, AssetManager assets) {
        this.tokens = tokens;
        this.assets = assets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.check_layout, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        String token = tokens.get(position);
        String text = token;
        String costText = "";
        String[] subTokens = OrderParser.ParseItem(token);
        if (position == 0)
        {
            String coffeeName = subTokens[1];

            text = "НАИМЕНОВАНИЕ:    " + coffeeName;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 0, 12, 12);
            viewHolder.view.setLayoutParams(params);
        }
        else if (position == tokens.size() - 1)
        {
            String cost = subTokens[1];

            text = "ИТОГО:    " + cost + " ₽";

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 12, 12, 0);
            viewHolder.view.setLayoutParams(params);
        }
        else
        {
            String addName = subTokens[0];
            String count = subTokens[1];
            String cost = subTokens[2];

            text = "ДОБАВКА:    " + addName;
            costText = "    ----- x" + count + "    = " + cost + " ₽";
        }
        viewHolder.text.setText(text);
        viewHolder.cost.setText(costText);
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        private TextView text;
        private TextView cost;
        private View view;

        public ViewHolder(View view) {
            super(view);
//            view.setOnClickListener(this);
            this.view = view;

            Typeface font = Typeface.createFromAsset(assets, "fonts/peace_sans_webfont.ttf");

            text = view.findViewById(R.id.check_line_text);
            text.setTypeface(font);

            cost = view.findViewById(R.id.check_count_text);
            cost.setTypeface(font);
        }

//        @Override
//        public void onClick(View view) {
//            mListener.onClick(view, galleryList.get(getAdapterPosition()));
//        }
    }
}
