package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.entities.Dish;
import es.imposoft.foodit.entities.Menu;
import es.imposoft.foodit.entities.Section;
import es.imposoft.foodit.model.DishDTO;
import es.imposoft.foodit.model.MenuDTO;
import es.imposoft.foodit.singletons.MenuEditor;
import es.imposoft.foodit.model.SectionDTO;

public class DishActivity extends AppCompatActivity {

    List<Dish> availableDishes = new ArrayList<>();
    List<Menu> availableMenus = new ArrayList<>();
    List<Section> availableSections = new ArrayList<>();
    ListView dishList;
    Menu selectedMenu;
    Section selectedSection;
    private Bundle windowInfo;
    MenuEditor menuEditorInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dish);

        windowInfo = getIntent().getExtras();
        menuEditorInstance = MenuEditor.getInstance();
        dishList = findViewById(R.id.listView_availableDishes);

        int menuID = (int) windowInfo.get("MenuID");
        int sectionID = (int) windowInfo.get("SectionID");
        selectedMenu = getSelectedMenu(menuID);
        selectedSection = getSelectedSection(sectionID);
        availableDishes = selectedSection.getDishes();
        fillSectionListView();

        //here u can use clickListener
        dishList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), DishCreationActivity.class);
            intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
            intent.putExtra("SectionID", (int) windowInfo.get("SectionID"));
            intent.putExtra("DishID", availableDishes.get(position).getId());
            startActivity(intent);
            finish();
        });
    }

    public void createDish(View view) {
        Intent intent = new Intent(getApplicationContext(), DishCreationActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        intent.putExtra("SectionID", (int) windowInfo.get("SectionID"));
        startActivity(intent);
        finish();
    }

    public void editSection(View view) {
        Intent intent = new Intent(this, SectionCreationActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        intent.putExtra("SectionID", (int) windowInfo.get("SectionID"));
        startActivity(intent);
        this.finish();
    }

    private void fillSectionListView() {
        ArrayAdapter<Dish> arrayAdapter = new ArrayAdapter<Dish>(this, android.R.layout.simple_selectable_list_item, availableDishes){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                //if(availableDishes.get(position).getId() < 0) { view.setBackgroundColor(Color.YELLOW); }
                if(availableDishes.get(position).isEdited()) {
                    view.setBackgroundColor(Color.YELLOW);
                }
                return view;
            }
        };
        dishList.setAdapter(arrayAdapter);
    }

    private Menu getSelectedMenu(int id){
        availableMenus = menuEditorInstance.getSavedMenus();
        for (Menu menu : availableMenus)
            if(menu.getId() == id) return menu;
        return null;
    }

    private Section getSelectedSection(int id) {
        availableSections = selectedMenu.getSections();
        for (Section section : availableSections)
            if(section.getId() == id) return section;
        return null;
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, SectionActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        startActivity(intent);
        this.finish();
    }
}
