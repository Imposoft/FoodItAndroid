package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    static List<Allergen> allergenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_creation);

        allergenList = new ArrayList<>();

        spinnerSections = findViewById(R.id.spinner_sections);
        createSpinners();

        spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sectionSelected = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),"Seleccionado: " + sectionSelected, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                Toast.makeText(parent.getContext(),"No se ha seleccionado categoría", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createSpinners() {
        ArrayList<Section> availableSections = new ArrayList<>();
        //availableSections = getAvailableSections();
        ArrayList<String> arrayList = new ArrayList<>();
        for(Section section : availableSections) arrayList.add(section.getName());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSections.setAdapter(arrayAdapter);

    }

    public void openAllergensPopup(View view) {
        Intent intent = new Intent(this, PopupActivity.class);
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
        Dish dish = new Dish(0, "name", "description", allergenList, 6.5);
        System.out.println(dish.toString());
    }

}
