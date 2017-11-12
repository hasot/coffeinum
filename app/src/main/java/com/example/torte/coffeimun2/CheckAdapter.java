package com.example.torte.coffeimun2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.torte.coffeimun2.adapter.CoffeHouseAdapter;
import com.example.torte.coffeimun2.interfaces.RecyclerViewClickListener;
import com.example.torte.coffeimun2.model.Menu;

import java.util.ArrayList;

/**
 * Created by torte on 12.11.2017.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder>{

    private ArrayList<String> tokens;
    private Context context;

    public CheckAdapter(Context context, ArrayList<String> tokens) {
        this.tokens = tokens;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.title.setText(galleryList.get(position).name);
//        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        ImageLoader.AddListener(galleryList.get(position).img, bitmap -> viewHolder.img.setImageBitmap(bitmap));

    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{
        private TextView title;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);
//            view.setOnClickListener(this);
            //TODO
//            title = (TextView)view.findViewById(R.id.title);
//            img = (ImageView) view.findViewById(R.id.img);
        }

//        @Override
//        public void onClick(View view) {
//            mListener.onClick(view, galleryList.get(getAdapterPosition()));
//        }
    }
}
