
package com.pyplyn.pawan.ecommerce.Category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CategoryMenuApi {

    String base_url = "http://dawaipedia.com/pawanEcommerce/rest/V1/";
    String token = "bearer n25y8h4tra2ydcu1bowu0bqk72h14wq2";

    @GET("categories")
    Call<CategoryMenu> getCategoryMenu(@Header("Authorization") String token);


}
