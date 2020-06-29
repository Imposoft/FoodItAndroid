package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.interfaces.FoodItAPI;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity {

    List<Menu> availableMenus = new ArrayList<>();
    ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuList = findViewById(R.id.listView_availableMenus);
        availableMenus = MenuEditor.getInstance().getSavedMenus();

        fillMenuListView();

    }

    public void createMenu(View view) {
        startActivity(new Intent(this, MenuCreationActivity.class));
        this.finish();
    }

    public void loadMenus(View view) {
        loadMenu();
        refresh();
    }

    public void saveMenus(View view) {
        for (Menu menu : availableMenus)
            postTestMenu(menu);
    }

    private void loadMenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);

        Call<Menu> menuCall = foodItAPI.loadMenu();
        menuCall.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Menu menu = response.body();
                Toast.makeText(MenuActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postTestMenu(Menu menu) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);

        Call<Menu> menuCall = foodItAPI.createNewMenu(menu);
        menuCall.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MenuActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillMenuListView() {
        List<String> arrayList = new ArrayList<>();
        for(Menu menu : availableMenus) arrayList.add(menu.getName());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuList.setAdapter(arrayAdapter);
    }

    private void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
