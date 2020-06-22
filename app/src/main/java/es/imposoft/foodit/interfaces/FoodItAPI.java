package es.imposoft.foodit.interfaces;

import es.imposoft.foodit.model.Menu;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodItAPI {
    @POST("createMenu")
    Call<Menu> createNewMenu(@Body Menu menu);

    @GET("loadMenu")
    Call<Menu> loadMenu();


}
