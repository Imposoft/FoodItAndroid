package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;

public class MenuCreationActivity extends AppCompatActivity {

    Menu menu;
    MenuEditor menuEditor;
    EditText name, description;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creation);


        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
    }

    public void saveMenu(View view) {
        menu = new Menu(null, id, name.getText().toString(), description.getText().toString());
        MenuEditor.getInstance().saveMenu(menu);
        System.out.println(menu.getName());
        startActivity(new Intent(this, SelectionActivity.class));
        this.finish();
    }
}
