package com.example.torte.coffeimun2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by torte on 12.11.2017.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder>{

    private ArrayList<String> tokens;

    public CheckAdapter(ArrayList<String> tokens) {
        this.tokens = tokens;
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
        String[] subTokens = OrderParser.ParseItem(token);
        if (position == 0)
        {
            String coffeeName = subTokens[1];

            text = "НАИМЕНОВАНИЕ: " + coffeeName;
        }
        else if (position == tokens.size() - 1)
        {
            String cost = subTokens[1];

            text = "ИТОГО: " + cost + " ₽";
        }
        else
        {
            String addName = subTokens[0];
            String count = subTokens[1];
            String cost = subTokens[2];

            text = "ДОБАВКА: " + addName + "    ----- x" + count + " " + cost + " ₽";
        }
        viewHolder.text.setText(text);
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        private TextView text;

        public ViewHolder(View view) {
            super(view);
//            view.setOnClickListener(this);
            text = view.findViewById(R.id.check_line_text);
        }

//        @Override
//        public void onClick(View view) {
//            mListener.onClick(view, galleryList.get(getAdapterPosition()));
//        }
    }
}
