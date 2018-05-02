package com.pyplyn.pawan.ecommerce;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WomenJacketsData extends AppCompatActivity {



    String[] name = {"Olivia 1/4 Zip Light Jacket","Juno Jacket","Neve Studio Dance Jacket","Nadia Elements Shell","Jade Yoga Jacket","Adrienne Trek Jacket","Inez Full Zip Jacket","Riona Full Zip Jacket","Ingrid Running Jacket","Augusta Pullover Jacket","Josie Yoga Jacket","Stellar Solar Jacket"};
    String[] prize = {"Prize $77","Prize $77","Prize $69","Prize $69","Prize $32","Prize $57","Prize $59","Prize $60","Prize $84","Prize $57","Prize $56","Prize $75"};
    String[] img = {"http://dawaipedia.com/Images/a.jpg","http://dawaipedia.com/Images/b.jpg","http://dawaipedia.com/Images/c.jpg","http://dawaipedia.com/Images/d.jpg","http://dawaipedia.com/Images/e.jpg","http://dawaipedia.com/Images/f.jpg","http://dawaipedia.com/Images/g.jpg","http://dawaipedia.com/Images/h.jpg","http://dawaipedia.com/Images/i.jpg","http://dawaipedia.com/Images/j.jpg","http://dawaipedia.com/Images/k.jpg","http://dawaipedia.com/Images/l.jpg"};


    Toolbar toolbar;

    TextView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_jackets_data);


        notification = (TextView) findViewById(R.id.notification);


        RecyclerView rv = (RecyclerView) findViewById(R.id.JacketsData);
        rv.setLayoutManager(new LinearLayoutManager(WomenJacketsData.this));
        rv.setAdapter(new JacketsCategoryAdapter(name,prize,img,this));

        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Jackets");
        toolbar.setTitleTextColor(Color.GRAY);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){


        toolbar.inflateMenu(R.menu.add_to_cart);
        /*getMenuInflater().inflate(R.menu.main_menu, menu);*/

        View your_menu_view = menu.findItem(R.id.menu_item1).getActionView(); your_menu_view.setOnClickListener(new View.OnClickListener()
        { @Override public void onClick(View v) {


        } });
        return super.onCreateOptionsMenu(menu);
    }

 /*   public void addToCart()
    {
        Toast.makeText(WomenJacketsData.this, "kjfndsnvfkdsnfkdsn", Toast.LENGTH_SHORT).show();
    }
*/

}
