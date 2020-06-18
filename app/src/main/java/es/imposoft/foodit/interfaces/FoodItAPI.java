package es.imposoft.foodit.interfaces;

import java.util.List;

import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodItAPI {
    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("greeting")
    Call<Menu> getTestMenu();

}
