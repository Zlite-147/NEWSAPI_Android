package com.example.newsapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapi.Model;
import com.example.newsapi.R;
import com.example.newsapi.WebViewer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomRecyclerAdapter  extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {


    private Context context;
    private List<Model> newCards;

    public CustomRecyclerAdapter(Context context, List<Model> newCards) {
        this.context = context;
        this.newCards = newCards;
    }

    @NonNull
    @Override
    public CustomRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomRecyclerAdapter.ViewHolder viewHolder, final int position) {


        viewHolder.itemView.setTag(newCards.get(position));

        final Model model=newCards.get(position);
        viewHolder.txt_title.setText(model.getTitle());
        viewHolder.txt_desc.setText(model.getDescription());
        viewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, WebViewer.class);
                intent.putExtra("URL",model.getUrl());
                context.startActivity(intent);
            }
        });

        Picasso.with(context).load(model.getUrlToImage()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return newCards.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView txt_title;
        public TextView txt_desc;
        public Button btn_more;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.img_url);
            txt_title=(TextView)itemView.findViewById(R.id.txt_title);
            txt_desc=(TextView)itemView.findViewById(R.id.txt_desc);
            btn_more=(Button)itemView.findViewById(R.id.btn_more);

        }
    }
}

