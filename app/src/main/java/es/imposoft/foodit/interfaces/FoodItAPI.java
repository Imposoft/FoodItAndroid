package es.imposoft.foodit.interfaces;

import java.util.List;

import es.imposoft.foodit.model.BarDTO;
import es.imposoft.foodit.model.MenuDTO;
import es.imposoft.foodit.model.UserDTO;
import okhttp3.ResponseBody;
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

    @POST("createMenus")
    Call<List<MenuDTO>> createMenus(List<MenuDTO> menus);

    @POST("createBars")
    Call<List<BarDTO>> createBars(List<BarDTO> bars);

    @POST("loginUser")
    Call<UserDTO> loginUser(@Body UserDTO newUser);


    @GET("loadMenu")
    Call<MenuDTO> loadMenu(@Query("id") int id);

    @GET("loadMenus")
    Call<List<MenuDTO>> loadMenus();

    @GET("loadBars")
    Call<List<BarDTO>> loadBars();


    @DELETE("deleteMenu")
    Call<Void> deleteMenu(@Path("id") int menuId);

    @DELETE("deleteBar")
    Call<Void> deleteBar(@Path("id") int barId);


}
