
package com.pyplyn.pawan.ecommerce.Category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pyplyn.pawan.ecommerce.R;

public class WomenAdapter extends RecyclerView.Adapter<WomenAdapter.CategoryViewHolder> {


    String[] categoryName;

    public WomenAdapter(String[] categoryName)
    {
        this.categoryName = categoryName;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.women_subcategory, parent, false);
        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        String category = categoryName[position];
        holder.name.setText(category);

    }

    @Override
    public int getItemCount() {

        return categoryName.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public CategoryViewHolder(View itemView)
        {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.finalCategory);
        }
    }
}
