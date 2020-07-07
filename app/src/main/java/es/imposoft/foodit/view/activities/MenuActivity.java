package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.entities.Menu;
import es.imposoft.foodit.interfaces.FoodItAPI;
import es.imposoft.foodit.logic.converter.ConverterUtil;
import es.imposoft.foodit.model.MenuDTO;
import es.imposoft.foodit.singletons.MenuEditor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity {

    List<Menu> availableMenus = new ArrayList<>();
    ListView menuList;
    MenuEditor menuEditorInstance;
    private Bundle windowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        menuList = findViewById(R.id.listView_availableMenus);
        menuEditorInstance = MenuEditor.getInstance();
        availableMenus = menuEditorInstance.getSavedMenus();

        //fillMenuListView();

        //TODO: comprobar si este codigo borra bien los menus
        windowInfo = getIntent().getExtras();
        if(windowInfo != null) {
            if(windowInfo.get("MenuIDtoDelete") != null)
                deleteMenu((int)windowInfo.get("MenuIDtoDelete"));
        }

        loadMenu();

        //here u can use clickListener
        menuList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), SectionActivity.class);
            intent.putExtra("MenuID", availableMenus.get(position).getId());
            startActivity(intent);
            finish();
        });

    }

    public void createMenu(View view) {
        startActivity(new Intent(this, MenuCreationActivity.class));
        this.finish();
    }

    public void loadMenus(View view) {
        loadMenu();
    }

    public void saveMenus(View view) {
        for (Menu menu : availableMenus) {
            postTestMenu(ConverterUtil.convertMenuDTO(menu));
        }
    }

    private void loadMenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);

        Call<List<MenuDTO>> menuCall = foodItAPI.loadMenus();
        menuCall.enqueue(new Callback<List<MenuDTO>>() {
            @Override
            public void onResponse(Call<List<MenuDTO>> call, Response<List<MenuDTO>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<MenuDTO> menus = response.body();
                List<Integer> idLoadedMenus = new ArrayList<>(), idAvailableMenus = new ArrayList<>();
                ;
                for (MenuDTO menu : menus) idLoadedMenus.add(menu.getId());
                for (Menu menu2 : availableMenus) idAvailableMenus.add(menu2.getId());

                int posicion = 0;
                for (Integer id : idLoadedMenus) {
                    if (!idAvailableMenus.contains(id))
                        //menus.get(posicion).setEdited(false);
                        menuEditorInstance.saveMenu(ConverterUtil.convertMenu(menus.get(posicion)));
                    posicion++;
                }

                availableMenus = menuEditorInstance.getSavedMenus();
                Toast.makeText(MenuActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
                fillMenuListView();
                //refresh();
            }

            @Override
            public void onFailure(Call<List<MenuDTO>> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postTestMenu(MenuDTO menu) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);
        Call<MenuDTO> menuCall = foodItAPI.createNewMenu(menu);
        menuCall.enqueue(new Callback<MenuDTO>() {
            @Override
            public void onResponse(Call<MenuDTO> call, Response<MenuDTO> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MenuActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MenuDTO> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void deleteMenu(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);
        Call<Void> deleteRequest = foodItAPI.deleteMenu(id);
        deleteRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MenuActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MenuActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MenuActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fillMenuListView() {
        ArrayAdapter<Menu> arrayAdapter = new ArrayAdapter<Menu>(this, android.R.layout.simple_selectable_list_item, availableMenus) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                //if (availableMenus.get(position).getId() < 0) {
                if(availableMenus.get(position).isEdited()) {
                    view.setBackgroundColor(Color.YELLOW);
                }
                return view;
            }
        };
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
