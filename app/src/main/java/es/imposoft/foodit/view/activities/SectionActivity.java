package es.imposoft.foodit.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class SectionActivity extends AppCompatActivity {

    List<Menu> availableMenus = new ArrayList<>();
    List<Section> availableSections = new ArrayList<>();
    ListView sectionList;
    Menu selectedMenu;
    private Bundle windowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_section);

        windowInfo = getIntent().getExtras();

        sectionList = findViewById(R.id.listView_availableSections);

        //Obtener todas las secciones de todos los menus en una misma lista
        /*for (Menu menu : availableMenus)
            availableSections.addAll(menu.getSections());*/

        int menuID = (int) windowInfo.get("MenuID");
        selectedMenu = getSelectedMenu(menuID);
        availableSections = selectedMenu.getSections();

        fillSectionListView();

        //here u can use clickListener
        sectionList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), DishActivity.class);
            intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
            intent.putExtra("SectionID", availableSections.get(position).getId());
            startActivity(intent);
            finish();
        });
    }

    public void createSection(View view) {
        Intent intent = new Intent(getApplicationContext(), SectionCreationActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        startActivity(intent);
        finish();
    }

    public void editMenu(View view) {
        Intent intent = new Intent(this, MenuCreationActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        startActivity(intent);
        this.finish();

    }

    public void addActual(View view) {
    }

    private void fillSectionListView() {
        ArrayAdapter<Section> arrayAdapter = new ArrayAdapter<Section>(this, android.R.layout.simple_selectable_list_item, availableSections){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                if(availableSections.get(position).getId() < 0) { view.setBackgroundColor(Color.YELLOW); }
                return view;
            }
        };
        sectionList.setAdapter(arrayAdapter);
    }

    private Menu getSelectedMenu(int id){
        availableMenus = MenuEditor.getInstance().getSavedMenus();
        for (Menu menu : availableMenus)
            if(menu.getId() == id) return menu;
        return null;
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        this.finish();
    }
}
