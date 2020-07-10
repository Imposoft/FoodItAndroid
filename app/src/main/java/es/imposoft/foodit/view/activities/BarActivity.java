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
import es.imposoft.foodit.entities.Bar;
import es.imposoft.foodit.entities.Menu;
import es.imposoft.foodit.interfaces.FoodItAPI;
import es.imposoft.foodit.logic.converter.ConverterUtil;
import es.imposoft.foodit.model.BarDTO;
import es.imposoft.foodit.model.MenuDTO;
import es.imposoft.foodit.singletons.BarEditor;
import es.imposoft.foodit.singletons.MenuEditor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BarActivity extends AppCompatActivity {

    List<Bar> availableBars = new ArrayList<>();
    ListView barList;
    BarEditor barEditorInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        barList = findViewById(R.id.listView_availableBars);
        barEditorInstance = BarEditor.getInstance();
        availableBars = barEditorInstance.getSavedBars();

        //TODO: comprobar si este codigo borra bien los menus
        Bundle windowInfo = getIntent().getExtras();
        if(windowInfo != null) {
            if(windowInfo.get("BarIDtoDelete") != null) {}
                deleteBar((int) windowInfo.get("BarIDtoDelete"));
        }


        //here u can use clickListener
        barList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.putExtra("BarID", availableBars.get(position).getId());
            startActivity(intent);
            finish();
        });

    }

    public void createBar(View view) {
        startActivity(new Intent(this, BarCreationActivity.class));
        this.finish();
    }

    public void loadBars(View view) {
        loadBar();
    }

    public void saveBars(View view) {
        List<BarDTO> barsToPost = new ArrayList<>();
        for (Bar bar : availableBars) {
            barsToPost.add(ConverterUtil.convertBarDTO(bar));
        }
        postBars(barsToPost);
    }

    private void loadBar() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/") // baseUrl("http://imposoft.es/" + user.getUsername() + bar.getName())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);

        Call<List<BarDTO>> barCall = foodItAPI.loadBars();
        barCall.enqueue(new Callback<List<BarDTO>>() {
            @Override
            public void onResponse(Call<List<BarDTO>> call, Response<List<BarDTO>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(BarActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<BarDTO> bars = response.body();
                List<Integer> idLoadedBars = new ArrayList<>(), idAvailableBars = new ArrayList<>();
                ;
                for (BarDTO bar : bars) idLoadedBars.add(bar.getId());
                for (Bar bar2 : availableBars) idAvailableBars.add(bar2.getId());

                int posicion = 0;
                for (Integer id : idLoadedBars) {
                    if (!idAvailableBars.contains(id))
                        barEditorInstance.saveBar(ConverterUtil.convertBar(bars.get(posicion)));
                    posicion++;
                }

                availableBars = barEditorInstance.getSavedBars();
                Toast.makeText(BarActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
                fillBarListView();

            }

            @Override
            public void onFailure(Call<List<BarDTO>> call, Throwable t) {
                Toast.makeText(BarActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postBars(List<BarDTO> bars) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/") // baseUrl("http://imposoft.es/" + user.getUsername() + bar.getName())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);
        Call<List<BarDTO>> menusCall = foodItAPI.createBars(bars);
        menusCall.enqueue(new Callback<List<BarDTO>>() {
            @Override
            public void onResponse(Call<List<BarDTO>> call, Response<List<BarDTO>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(BarActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BarActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
                barEditorInstance.getSavedBars().clear();
                loadBar();
            }

            @Override
            public void onFailure(Call<List<BarDTO>> call, Throwable t) {
                Toast.makeText(BarActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void deleteBar(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/") // baseUrl("http://imposoft.es/" + user.getUsername() + bar.getName())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);
        Call<Void> deleteRequest = foodItAPI.deleteBar(id);
        deleteRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(BarActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BarActivity.this, "Éxito: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(BarActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillBarListView() {
        ArrayAdapter<Bar> arrayAdapter = new ArrayAdapter<Bar>(this, android.R.layout.simple_selectable_list_item, availableBars) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if(availableBars.get(position).isEdited()) {
                    view.setBackgroundColor(Color.YELLOW);
                }
                return view;
            }
        };
        barList.setAdapter(arrayAdapter);
    }
}
