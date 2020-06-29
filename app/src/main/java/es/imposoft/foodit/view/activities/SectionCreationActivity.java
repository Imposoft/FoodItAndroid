package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
    Section desiredSection;
    Menu desiredMenu;
    private Bundle windowInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_creation);

        availableMenus = MenuEditor.getInstance().getSavedMenus();

        windowInfo = getIntent().getExtras();

        if (windowInfo != null) {
            List<Menu> menus = MenuEditor.getInstance().getSavedMenus();
            for (Menu menu : menus) {
                if (menu.getId() == (int) windowInfo.get("MenuID")) desiredMenu = menu;
            }
            if(windowInfo.get("SectionID") != null)
                for (Section section : desiredMenu.getSections()) {
                    if (section.getId() == (int) windowInfo.get("SectionID")) desiredSection = section;
            }
        }

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);

        if (desiredSection != null) {
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
}
