package com.ilovenougat.classes.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilovenougat.classes.R;
import com.ilovenougat.classes.asynctasks.SearchAsynctask;
import com.ilovenougat.classes.dto.ProductDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<ProductDTO> feedItemList;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<ProductDTO> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_result_row, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ProductDTO productDTO = feedItemList.get((int)v.getTag());
                //Toast.makeText(mContext, productDTO.getProductName(), Toast.LENGTH_SHORT).show();
            }
        });

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        ProductDTO feedItem = feedItemList.get(i);

        //Download image using picasso library
        Picasso.with(mContext).load(feedItem.getThumbnailImageUrl())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(customViewHolder.imageView);

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getProductName() + " (" + feedItem.getColorId() +")") +
                "\n" + Html.fromHtml(feedItem.getPrice()));

        customViewHolder.imageView.setTag(i);
        customViewHolder.textView.setTag(i);

        customViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDTO productDTO = feedItemList.get((int)v.getTag());
                new SearchAsynctask(mContext, productDTO.getProductId()).execute();
            }
        });

        customViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDTO productDTO = feedItemList.get((int)v.getTag());
                new SearchAsynctask(mContext, productDTO.getProductId()).execute();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }




}