package com.pyplyn.pawan.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pyplyn.pawan.ecommerce.Category.CategoryAdapter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class JacketsCategoryAdapter extends RecyclerView.Adapter<JacketsCategoryAdapter.CategoryViewHolder> {


    String[] categoryName;
    String[] prize;
    String[] image;
    private Context context;
    int count=0;


    public JacketsCategoryAdapter(String[] categoryName, String[] prize, String[] image, Context context) {
        this.categoryName = categoryName;
        this.prize = prize;
        this.image = image;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.list, parent, false);
        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        String category = categoryName[position];
        holder.name.setText(category);
        String pr = prize[position];
        holder.prize.setText(pr);

        View newView =LayoutInflater.from(context).inflate(R.layout.your_custom_menu_layout, null);
        TextView textView= (TextView) newView.findViewById(R.id.notification);
        textView.setText("hello notification");

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++count;
            }
        });

        String url = image[position];

        Picasso.with(context)
                .load(url.toString())
                .into(holder.iv);
       /* bitmap = getBitmapFromUrl(image[position]);
        holder.iv.setImageBitmap(bitmap);
*/

    }



    @Override
    public int getItemCount() {

        return categoryName.length;

    }

    public static class RetrieveFeedTask {
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {


        TextView name, prize;
        ImageView iv,text;
        Button button;




        public CategoryViewHolder(View itemView) {
            super(itemView);


            text= (ImageView) itemView.findViewById(R.id.notification);
            name = (TextView) itemView.findViewById(R.id.finalCategory);
            button = (Button) itemView.findViewById(R.id.addCart);
            Typeface typeface = Typeface.createFromAsset(name.getContext().getAssets(), "third.ttf");
            name.setTypeface(typeface);
            prize = (TextView) itemView.findViewById(R.id.prize);
            prize.setTypeface(typeface);
            iv = (ImageView) itemView.findViewById(R.id.image);
/*
           notification = (TextView) itemView.findViewById(R.id.notification);
            button = (Button) itemView.findViewById(R.id.addCart);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   notification.setText("pawan");
                }
            });*/


        }


    }

}
