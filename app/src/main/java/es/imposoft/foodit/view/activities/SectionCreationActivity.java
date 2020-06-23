package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class SectionCreationActivity extends AppCompatActivity {

    List<Menu> savedMenus;
    EditText name, description;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_creation);

        savedMenus = MenuEditor.getInstance().getSavedMenus();

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
    }

    public void saveSection(View view) {
        Section section = new Section(id, name.getText().toString(), description.getText().toString(), null);
        savedMenus.get(0).addSectionToMenu(section);
        System.out.println(section.getName());
        startActivity(new Intent(this, SelectionActivity.class));
        this.finish();

    }
}
