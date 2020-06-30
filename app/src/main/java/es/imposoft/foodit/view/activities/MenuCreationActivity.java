package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.IDSingleton;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;

public class MenuCreationActivity extends AppCompatActivity {

    Menu menu;
    EditText name, description;
    int id;
    private Bundle windowInfo;
    Menu desiredMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_creation);

        windowInfo = getIntent().getExtras();

        if(windowInfo != null) {
            List<Menu> menus = MenuEditor.getInstance().getSavedMenus();
            for (Menu menu : menus) {
                if(menu.getId() == (int) windowInfo.get("MenuID")) desiredMenu = menu;
            }
        }

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
        Button delete = findViewById(R.id.button_delete);
        Button create = findViewById(R.id.button_create);
        delete.setVisibility(View.GONE);
        create.setText("Crear");

        if(desiredMenu != null) {
            delete.setVisibility(View.VISIBLE);
            create.setText("Actualizar");
            name.setText(desiredMenu.getName());
            description.setText(desiredMenu.getDescription());
        }

    }

    public void saveMenu(View view) {
        String strName = name.getText().toString();
        if(!TextUtils.isEmpty(strName) && desiredMenu == null) {
            id = IDSingleton.getInstance().getIDMenu();
            menu = new Menu(id, name.getText().toString(), description.getText().toString());
            MenuEditor.getInstance().saveMenu(menu);
            startActivity(new Intent(this, MenuActivity.class));
            this.finish();
        } else if (!TextUtils.isEmpty(strName) && desiredMenu != null) {
            desiredMenu.setDescription(description.getText().toString());
            desiredMenu.setName(name.getText().toString());
            startActivity(new Intent(this, MenuActivity.class));
            this.finish();
        } else {
            name.setError("You must enter the name");
        }
    }

    public void deleteMenu(View view) {
        List<Menu> availableMenus = MenuEditor.getInstance().getSavedMenus();
        availableMenus.remove(desiredMenu);
        goBack(view);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        this.finish();
    }
}
