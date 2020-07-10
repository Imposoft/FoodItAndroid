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
import es.imposoft.foodit.entities.Bar;
import es.imposoft.foodit.singletons.BarEditor;
import es.imposoft.foodit.singletons.IDSingleton;

public class BarCreationActivity extends AppCompatActivity {

    private BarEditor barEditorInstance;
    private Bar desiredBar;
    private EditText name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_creation);

        Bundle windowInfo = getIntent().getExtras();

        barEditorInstance = BarEditor.getInstance();

        if (windowInfo != null) {
            List<Bar> bars = barEditorInstance.getSavedBars();
            for (Bar bar : bars) {
                if (bar.getId() == (int) windowInfo.get("BarID")) desiredBar = bar;
            }
        }

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
        Button delete = findViewById(R.id.button_delete);
        Button create = findViewById(R.id.button_create);
        delete.setVisibility(View.GONE);
        create.setText("Crear");

        if (desiredBar != null) {
            delete.setVisibility(View.VISIBLE);
            create.setText("Actualizar");
            name.setText(desiredBar.getName());
            description.setText(desiredBar.getDescription());
        }
    }

    public void saveBar(View view) {
        int id = IDSingleton.getInstance().getIDBar();
        String strName = name.getText().toString();
        if (!TextUtils.isEmpty(strName) && desiredBar == null) {
            Bar bar = new Bar(id, name.getText().toString(), description.getText().toString());
            barEditorInstance.saveBar(bar);
            startActivity(new Intent(this, BarActivity.class));
            this.finish();
        } else if (!TextUtils.isEmpty(strName) && desiredBar != null) {
            desiredBar.setDescription(description.getText().toString());
            desiredBar.setName(name.getText().toString());
            desiredBar.setEdited(true);
            startActivity(new Intent(this, MenuActivity.class));
            this.finish();
        } else {
            name.setError("You must enter the name");
        }
    }

    public void deleteBar(View view) {
        Intent intent = new Intent(this, BarActivity.class);
        intent.putExtra("BarIDtoDelete", desiredBar.getId());
        startActivity(intent);
        this.finish();
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, BarActivity.class);
        startActivity(intent);
        this.finish();
    }
}