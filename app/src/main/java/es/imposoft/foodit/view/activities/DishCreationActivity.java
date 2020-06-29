package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Allergen;
import es.imposoft.foodit.model.Dish;
import es.imposoft.foodit.model.IDSingleton;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class DishCreationActivity extends AppCompatActivity {

    Spinner spinnerSections;
    EditText name, description, price;
    static List<Allergen> allergenList;
    Section sectionSelected;
    List<Menu> availableMenus = new ArrayList<>();
    List<Section> availableSections = new ArrayList<>();
    Dish desiredDish;

    int id;
    private Bundle windowInfo;
    private Menu desiredMenu;
    private Section desiredSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_creation);

        allergenList = new ArrayList<>();
        availableMenus = MenuEditor.getInstance().getSavedMenus();

        windowInfo = getIntent().getExtras();

        if (windowInfo != null) {
            List<Menu> menus = MenuEditor.getInstance().getSavedMenus();
            for (Menu menu : menus) {
                if (menu.getId() == (int) windowInfo.get("MenuID")) desiredMenu = menu;
            }
            for (Section section : desiredMenu.getSections()) {
                if (section.getId() == (int) windowInfo.get("SectionID")) desiredSection = section;
            }
            if(windowInfo.get("DishID") != null)
                for (Dish dish : desiredSection.getDishes()) {
                    if (dish.getId() == (int) windowInfo.get("DishID")) desiredDish = dish;
            }
        }

        name = findViewById(R.id.editText_name);
        description = findViewById(R.id.editText_description);
        price = findViewById(R.id.editText_price);

        if (desiredDish != null) {
            name.setText(desiredDish.getName());
            description.setText(desiredDish.getDescription());
            price.setText(String.valueOf(desiredDish.getPrice()));
            allergenList = desiredDish.getAllergens();
        }
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
        String strName = name.getText().toString();
        String strDescription = description.getText().toString();
        String strPrice = price.getText().toString();
        if(!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strDescription) && !TextUtils.isEmpty(strPrice) && desiredDish == null) {
            id = IDSingleton.getInstance().getIDDish();
            Dish dish = new Dish(id, name.getText().toString(), description.getText().toString(), allergenList, Double.parseDouble(String.valueOf(price.getText())));
            desiredSection.addDishToSection(dish);
            Intent intent = new Intent(this, DishActivity.class);
            intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
            intent.putExtra("SectionID", (int) windowInfo.get("SectionID"));
            startActivity(intent);
            this.finish();
        } else if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strDescription) && !TextUtils.isEmpty(strPrice) && desiredDish != null) {
            desiredDish.setDescription(description.getText().toString());
            desiredDish.setName(name.getText().toString());
            desiredDish.setPrice(Double.parseDouble(price.getText().toString()));
            desiredDish.setAllergens(allergenList);
            Intent intent = new Intent(this, DishActivity.class);
            intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
            intent.putExtra("SectionID", (int) windowInfo.get("SectionID"));
            startActivity(intent);
            this.finish();
        } else {
            if(TextUtils.isEmpty(strName)) name.setError("You must enter the name");
            if(TextUtils.isEmpty(strDescription)) description.setError("You must enter the ingredients");
            if(TextUtils.isEmpty(strPrice)) price.setError("You must enter the price");
        }

    }
}
