package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.imposoft.foodit.R;
import es.imposoft.foodit.entities.User;
import es.imposoft.foodit.interfaces.FoodItAPI;
import es.imposoft.foodit.logic.converter.ConverterUtil;
import es.imposoft.foodit.model.UserDTO;
import es.imposoft.foodit.singletons.IDSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginActivity extends AppCompatActivity {

    EditText username, password;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_login);

        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
    }

    public void loginUser(View view) {
        int iduser = IDSingleton.getInstance().getIDUser();
        String strUsername = username.getText().toString(), strPassword = password.getText().toString();
        user = new User(iduser, strUsername, strPassword);
        if(!TextUtils.isEmpty(strUsername) && !TextUtils.isEmpty(strPassword)) {
            UserDTO userDTO = ConverterUtil.convertUserDTO(user);
            loginUser(userDTO);
        } else {
            if (!TextUtils.isEmpty(strUsername)) username.setError("You must enter the username");
            else password.setError("You must enter the password");
        }
    }

    private void loginUser(UserDTO user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://imposoft.es:8080/") // baseUrl("http://imposoft.es/" + user.getUsername())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodItAPI foodItAPI = retrofit.create(FoodItAPI.class);
        Call<UserDTO> deleteRequest = foodItAPI.loginUser(user);
        deleteRequest.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UserLoginActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(UserLoginActivity.this, "Bienvenido " + user.getUsername() + "!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), BarActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast.makeText(UserLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
