package es.imposoft.foodit.interfaces;

import java.util.List;

import es.imposoft.foodit.model.MenuDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodItAPI {
    @POST("createMenu")
    Call<MenuDTO> createNewMenu(@Body MenuDTO menu);

    @GET("loadMenu")
    Call<MenuDTO> loadMenu(@Query("id") int id);

    @GET("loadMenus")
    Call<List<MenuDTO>> loadMenus();

    @DELETE("deleteMenu")
    Call<Void> deleteMenu(@Path("id") int menuId);

}
