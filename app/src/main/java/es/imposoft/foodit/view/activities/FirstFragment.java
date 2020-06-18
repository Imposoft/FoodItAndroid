package es.imposoft.foodit.view.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import es.imposoft.foodit.R;
import es.imposoft.foodit.interfaces.FoodItAPI;
import es.imposoft.foodit.model.Menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {

    EditText textBox;
    private TextView console;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textBox = view.findViewById(R.id.textMenu);
        console = view.findViewById(R.id.console);

        view.findViewById(R.id.button_uploadMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You uploaded the menu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                String textMenu = textBox.getText().toString();

                //getTestMenu();
                Menu menu = new Menu(textMenu);
                postTestMenu(menu);
                //NavHostFragment.findNavController(FirstFragment.this)
                        //.navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    private void getTestMenu() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);

        Call<Menu> menuCall = foodItAPI.getTestMenu();
        menuCall.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                if(!response.isSuccessful()) {
                    console.setText("Error in execution" + response.code());
                    return;
                }
                Menu menu = response.body();
                console.setText("Tu menu" + menu.getMenuText());
                System.out.println(menu.getMenuText());
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                console.setText(t.getMessage());
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
                    console.setText("Error: " + response.code());
                    return;
                }
                Menu menu = response.body();
                console.setText("Éxito: " + response.code());
                System.out.println(menu.getMenuText());
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                console.setText(t.getMessage());
            }
        });
    }
}
