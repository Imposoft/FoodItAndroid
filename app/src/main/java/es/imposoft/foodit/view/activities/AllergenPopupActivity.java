package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Allergen;

public class AllergenPopupActivity extends AppCompatActivity {

    private static boolean[] checked = new boolean[14];
    List<CheckBox> checkboxes = new ArrayList<>();
    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        c1 = findViewById(R.id.GLUTEN); checkboxes.add(c1);
        c2 = findViewById(R.id.CRUSTACEOS); checkboxes.add(c2);
        c3 = findViewById(R.id.HUEVOS); checkboxes.add(c3);
        c4 = findViewById(R.id.PESCADO); checkboxes.add(c4);
        c5 = findViewById(R.id.CACAHUETES); checkboxes.add(c5);
        c6 = findViewById(R.id.SOJA); checkboxes.add(c6);
        c7 = findViewById(R.id.LACTEOS); checkboxes.add(c7);
        c8 = findViewById(R.id.FRUTOS_DE_CASCARA); checkboxes.add(c8);
        c9 = findViewById(R.id.APIO); checkboxes.add(c9);
        c10 = findViewById(R.id.MOSTAZA); checkboxes.add(c10);
        c11 = findViewById(R.id.GRANOS_DE_SESAMO); checkboxes.add(c11);
        c12 = findViewById(R.id.SULFITOS); checkboxes.add(c12);
        c13 = findViewById(R.id.ALTRAMUCES); checkboxes.add(c13);
        c14 = findViewById(R.id.MOLUSCOS); checkboxes.add(c14);

        Arrays.fill(checked, false);

        isSelectedPreviously();
        for(int i = 0; i < checked.length; i++) {
            if (checked[i]) checkboxes.get(i).setChecked(true);
        }


    }


    public void createPreferenceList(View view) {
        List<Allergen> allergens = DishCreationActivity.getAllergenList();
        allergens.clear();
        int i = 0;
        for(CheckBox c : checkboxes) {
            if (c.isChecked()) allergens.add(Allergen.values()[i]);
            i++;
        }
        DishCreationActivity.setAllergens(allergens);
        System.out.println(allergens.toString());
        finish();
    }

    private void isSelectedPreviously() {
        List<Allergen> allergens;
        allergens = DishCreationActivity.getAllergenList();
        for (Allergen a : allergens) {
            switch (a) {
                case GLUTEN:
                   checked[0] = true;
                   break;
                case CRUSTACEOS:
                    checked[1] = true;
                    break;
                case HUEVOS:
                    checked[2] = true;
                    break;
                case PESCADO:
                    checked[3] = true;
                    break;
                case CACAHUETES:
                    checked[4] = true;
                    break;
                case SOJA:
                    checked[5] = true;
                    break;
                case LACTEOS:
                    checked[6] = true;
                    break;
                case FRUTOS_DE_CASCARA:
                    checked[7] = true;
                    break;
                case APIO:
                    checked[8] = true;
                    break;
                case MOSTAZA:
                    checked[9] = true;
                    break;
                case GRANOS_DE_SESAMO:
                    checked[10] = true;
                    break;
                case SULFITOS:
                    checked[11] = true;
                    break;
                case ALTRAMUCES:
                    checked[12] = true;
                    break;
                case MOLUSCOS:
                    checked[13] = true;
                    break;
            }
        }
    }
}
