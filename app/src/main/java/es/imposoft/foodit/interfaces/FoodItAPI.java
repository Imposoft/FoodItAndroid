package es.imposoft.foodit.interfaces;

import java.util.List;

import es.imposoft.foodit.model.Menu;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FoodItAPI {
    @POST("createMenu")
    Call<Menu> createNewMenu(@Body Menu menu);

    @GET("loadMenu")
    Call<Menu> loadMenu(@Query("id") int id);

    @GET("loadMenus")
    Call<List<Menu>> loadMenus();

}
