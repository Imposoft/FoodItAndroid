package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.IDSingleton;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class SectionCreationActivity extends AppCompatActivity {

    List<Menu> availableMenus;
    EditText name, description;
    int id;
    Menu desiredMenu;
    Section desiredSection;

    private Bundle windowInfo;
    MenuEditor menuEditorInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_creation);

        menuEditorInstance = MenuEditor.getInstance();
        availableMenus = menuEditorInstance.getSavedMenus();

        windowInfo = getIntent().getExtras();

        if (windowInfo != null) {
            for (Menu menu : availableMenus) {
                if (menu.getId() == (int) windowInfo.get("MenuID")) desiredMenu = menu;
            }
            if(windowInfo.get("SectionID") != null)
                for (Section section : desiredMenu.getSections()) {
                    if (section.getId() == (int) windowInfo.get("SectionID")) desiredSection = section;
            }
        }

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
        Button delete = findViewById(R.id.button_delete);
        Button create = findViewById(R.id.button_create);
        delete.setVisibility(View.GONE);
        create.setText("Crear");

        if (desiredSection != null) {
            delete.setVisibility(View.VISIBLE);
            create.setText("Actualizar");
            name.setText(desiredSection.getName());
            description.setText(desiredSection.getDescription());
        }
    }

    public void saveSection(View view) {
        String strName = name.getText().toString();
        if (!TextUtils.isEmpty(strName) && desiredSection == null) {
            id = IDSingleton.getInstance().getIDSection();
            Section section = new Section(id, name.getText().toString(), description.getText().toString());
            desiredMenu.addSectionToMenu(section);
            Intent intent = new Intent(this, SectionActivity.class);
            intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
            startActivity(intent);
            this.finish();
        } else if (!TextUtils.isEmpty(strName) && desiredSection != null) {
            desiredSection.setDescription(description.getText().toString());
            desiredSection.setName(name.getText().toString());
            Intent intent = new Intent(this, SectionActivity.class);
            intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
            startActivity(intent);
            this.finish();
        } else {
            name.setError("You must enter the name");
        }

    }

    public void deleteMenu(View view) {
        List<Section> availableSections = desiredMenu.getSections();
        availableSections.remove(desiredSection);
        goBack(view);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, SectionActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        startActivity(intent);
        this.finish();
    }

}
