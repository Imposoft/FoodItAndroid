package es.imposoft.foodit.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Allergen;
import es.imposoft.foodit.model.Dish;
import es.imposoft.foodit.model.Section;

public class DishesCreationActivity extends AppCompatActivity {

    Spinner spinnerSections;
    EditText name, description, price;
    static List<Allergen> allergenList;
    Section sectionSelected;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_creation);

        allergenList = new ArrayList<>();

        spinnerSections = findViewById(R.id.spinner_sections);
        createSpinners();

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
        price = findViewById(R.id.editText_price);

        spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sectionSelected = Section.getSectionFromName(parent.getItemAtPosition(position).toString());
                Toast.makeText(parent.getContext(),"Seleccionado: " + sectionSelected.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                Toast.makeText(parent.getContext(),"No se ha seleccionado categoría", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createSpinners() {
        ArrayList<Section> availableSections = Section.getAvailableSections();
        ArrayList<String> arrayList = new ArrayList<>();
        for(Section section : availableSections) arrayList.add(section.getName());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSections.setAdapter(arrayAdapter);

    }

    public void openAllergensPopup(View view) {
        Intent intent = new Intent(this, AllergenPopupActivity.class);
        startActivity(intent);

    }

    public static List<Allergen> getAllergenList() {
        return allergenList;
    }

    public static void setAllergens(List<Allergen> list) {
        allergenList = list;
    }

    public void saveDish(View view) {
        System.out.println(allergenList.toString());
        Dish dish = new Dish(id, name.getText().toString(), description.getText().toString(), allergenList, Double.parseDouble(String.valueOf(price.getText())));
        addDishToSection(dish);
        System.out.println(dish.toString());
    }

    public void addDishToSection(Dish dish) {
        sectionSelected.getDishes().add(dish);
    }

}
