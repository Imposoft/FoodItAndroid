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

public class PopupActivity extends AppCompatActivity {

    private static boolean[] checked = new boolean[14];
    public static List<Allergen> selectedAllergens;
    List<CheckBox> checkboxes = new ArrayList<>();
    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14;

    static {
        Arrays.fill(checked, false);
    }


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

        for(int i = 0; i < checked.length; i++) {
            if (checked[i]) checkboxes.get(i).setChecked(true);
        }

        /*
        if (checked[0]) c1.setChecked(true);
        if (checked[1]) c2.setChecked(true);
        if (checked[2]) c3.setChecked(true);
        if (checked[3]) c4.setChecked(true);
        if (checked[4]) c5.setChecked(true);
        if (checked[5]) c6.setChecked(true);
        if (checked[6]) c7.setChecked(true);
        if (checked[7]) c8.setChecked(true);
        if (checked[8]) c9.setChecked(true);
        if (checked[9]) c10.setChecked(true);
        if (checked[10]) c11.setChecked(true);
        if (checked[11]) c12.setChecked(true);
        if (checked[12]) c13.setChecked(true);
        if (checked[13]) c14.setChecked(true);
         */

    }


    public void createPreferenceList(View view) {
        List<Allergen> allergens = new ArrayList<>();

        int i = 0;
        for(CheckBox c : checkboxes) {
            if (c.isChecked()) allergens.add(Allergen.values()[i]);
            i++;
        }

        /*
        if(c1.isChecked()) { allergens.add(Allergen.GLUTEN); }
        if(c2.isChecked()) { allergens.add(Allergen.CRUSTACEOS); }
        if(c3.isChecked()) { allergens.add(Allergen.HUEVOS); }
        if(c4.isChecked()) { allergens.add(Allergen.PESCADO); }
        if(c5.isChecked()) { allergens.add(Allergen.CACAHUETES); }
        if(c6.isChecked()) { allergens.add(Allergen.SOJA); }
        if(c7.isChecked()) { allergens.add(Allergen.LACTEOS); }
        if(c8.isChecked()) { allergens.add(Allergen.FRUTOS_DE_CASCARA); }
        if(c9.isChecked()) { allergens.add(Allergen.APIO); }
        if(c10.isChecked()) { allergens.add(Allergen.MOSTAZA); }
        if(c11.isChecked()) { allergens.add(Allergen.GRANOS_DE_SESAMO); }
        if(c12.isChecked()) { allergens.add(Allergen.SULFITOS); }
        if(c13.isChecked()) { allergens.add(Allergen.ALTRAMUCES); }
        if(c14.isChecked()) { allergens.add(Allergen.MOLUSCOS); }
         */

        PopupActivity.selectedAllergens = allergens;
        DishesCreationActivity.setAllergens(allergens);
        System.out.println(allergens.size());
        finish();
    }
}
