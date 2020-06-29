package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.imposoft.foodit.R;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void openCreateMenu(View view) {
        startActivity(new Intent(this, MenuCreationActivity.class));
        this.finish();
    }

    public void openCreateSection(View view) {
        startActivity(new Intent(this, SectionCreationActivity.class));
        this.finish();
    }

    public void openCreateDish(View view) {
        startActivity(new Intent(this, DishesCreationActivity.class));
        this.finish();
    }

    public void uploadChanges(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
