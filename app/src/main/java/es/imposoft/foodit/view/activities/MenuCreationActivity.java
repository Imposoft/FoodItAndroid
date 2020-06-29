package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class MenuCreationActivity extends AppCompatActivity {

    Menu menu;
    EditText name, description;
    int id = -1;
    private Bundle windowInfo;
    Menu desiredMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creation);

        //TODO: en funcion de si se le pasa un id o no, rellenar los campos de texto para editarlos
        windowInfo = getIntent().getExtras();
        if(windowInfo.get("MenuID") != null) {
            List<Menu> menus = MenuEditor.getInstance().getSavedMenus();
            for (Menu menu : menus) {
                if(menu.getId() == (int) windowInfo.get("MenuID")) desiredMenu = menu;
            }
        }

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);

        if(desiredMenu != null) {
            name.setText(desiredMenu.getName());
            description.setText(desiredMenu.getDescription());
        }

    }

    public void saveMenu(View view) {
        String strName = name.getText().toString();
        if(!TextUtils.isEmpty(strName)) {
            menu = new Menu(id, name.getText().toString(), description.getText().toString());
            MenuEditor.getInstance().saveMenu(menu);
            System.out.println(menu.getName());
            startActivity(new Intent(this, MenuActivity.class));
            this.finish();
        } else {
            name.setError("You must enter the name");
            return;
        }
    }
}
