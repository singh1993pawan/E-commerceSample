package com.pyplyn.pawan.ecommerce;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pyplyn.pawan.ecommerce.Category.CategoryAdapter;
import com.pyplyn.pawan.ecommerce.Category.CategoryMenu;
import com.pyplyn.pawan.ecommerce.Category.CategoryMenuApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {


    LinearLayout linearLayout;

    private Toolbar toolbar,tool;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pyplyn");
        toolbar.setTitleTextColor(Color.GRAY);

        //define Retrofit

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

                int size = list.getChildrenData().size();
                String[] data = new String[size];

                for (int i=0;i<size;i++)
                {
                    data[i] = list.getChildrenData().get(i).getName().toString();
                }
                //Data Show in Recycler View
                RecyclerView rv = (RecyclerView) findViewById(R.id.categoryList);
                rv.setLayoutManager(new LinearLayoutManager(Home.this));
                rv.setAdapter(new CategoryAdapter(data));
            }

            @Override
            public void onFailure(Call<CategoryMenu> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){


        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick(View v){

        Intent intent = new Intent(Home.this,CategoryWomen.class);
        startActivity(intent);
    }

}
