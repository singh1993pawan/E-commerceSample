package com.pyplyn.pawan.ecommerce;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.pyplyn.pawan.ecommerce.Category.CategoryMenu;
import com.pyplyn.pawan.ecommerce.Category.CategoryMenuApi;
import com.pyplyn.pawan.ecommerce.Category.CategoryWomenAdapter;
import com.pyplyn.pawan.ecommerce.Category.ChildData;
import com.pyplyn.pawan.ecommerce.Category.ChildData_;
import com.pyplyn.pawan.ecommerce.Category.WomenAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryWomen extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_women);


        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pyplyn");
        toolbar.setTitleTextColor(Color.GRAY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CategoryMenuApi.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryMenuApi categoryMenuApi = retrofit.create(CategoryMenuApi.class);

        Call<CategoryMenu> categoryMenuCall = categoryMenuApi.getCategoryMenu(CategoryMenuApi.token);

        categoryMenuCall.enqueue(new Callback<CategoryMenu>() {
            @Override
            public void onResponse(Call<CategoryMenu> call, Response<CategoryMenu> response) {

                CategoryMenu list = response.body();
                ChildData second = list.getChildrenData().get(1);
                int sample = second.getChildrenData().size();
                String[] data = new String[sample];
                for (int i=0;i<sample;i++)
                {
                    data[i] = second.getChildrenData().get(i).getName().toString();

                }

                        //Data Show in Recycler View
                        RecyclerView rv = (RecyclerView) findViewById(R.id.categorywomen);
                        rv.setLayoutManager(new LinearLayoutManager(CategoryWomen.this));
                        rv.setAdapter(new CategoryWomenAdapter(data));



                }


            @Override
            public void onFailure(Call<CategoryMenu> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClick(View v){

        Intent intent = new Intent(CategoryWomen.this,WomenCategory.class);
        startActivity(intent);


    }

}
