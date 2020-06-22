package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Section;

public class SectionCreationActivity extends AppCompatActivity {

    EditText name, description;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_creation);


        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
    }

    public void saveSection(View view) {
        Section section = new Section(id, name.getText().toString(), description.getText().toString(), null);
    }
}
