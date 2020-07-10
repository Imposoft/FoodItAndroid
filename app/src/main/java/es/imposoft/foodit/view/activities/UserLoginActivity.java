package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import es.imposoft.foodit.R;

public class UserLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_login);
    }

    public void registerAccount(View view) {
        startActivity(new Intent(this, UserRegisterActivity.class));
        this.finish();
    }

    public void registerNewUser(View view) {
    }

    public void loginUser(View view) {
    }
}
