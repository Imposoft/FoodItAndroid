package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class SectionCreationActivity extends AppCompatActivity {

    List<Menu> savedMenus;
    EditText name, description;
    int id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_creation);

        savedMenus = MenuEditor.getInstance().getSavedMenus();

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
    }

    public void saveSection(View view) {
        String strName = name.getText().toString();
        if(!TextUtils.isEmpty(strName)) {
        Section section = new Section(id, name.getText().toString(), description.getText().toString());
        savedMenus.get(0).addSectionToMenu(section);
        System.out.println(section.getName());
        startActivity(new Intent(this, SelectionActivity.class));
        this.finish();
        } else {
            name.setError("You must enter the name");
            return;
        }

    }
}
