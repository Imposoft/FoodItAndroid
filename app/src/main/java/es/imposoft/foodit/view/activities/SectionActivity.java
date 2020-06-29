package es.imposoft.foodit.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.R;
import es.imposoft.foodit.model.Menu;
import es.imposoft.foodit.model.MenuEditor;
import es.imposoft.foodit.model.Section;

public class SectionActivity extends AppCompatActivity {

    List<Menu> availableMenus = new ArrayList<>();
    List<Section>availableSections = new ArrayList<>();
    ListView sectionList;
    private Bundle windowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_section);

        windowInfo = getIntent().getExtras();

        sectionList = findViewById(R.id.listView_availableMenus);
        availableMenus = MenuEditor.getInstance().getSavedMenus();

        for (Menu menu : availableMenus) {
            availableSections.addAll(menu.getSections());
        }

        fillSectionListView();

        //here u can use clickListener
        sectionList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), SectionActivity.class);
            intent.putExtra("SectionID", availableSections.get(position).getId());
            startActivity(intent);
            finish();
        });
    }

    public void createSection(View view) {
        startActivity(new Intent(this, SectionCreationActivity.class));
        this.finish();
    }

    public void editMenu(View view) {
        Intent intent = new Intent(this, SectionCreationActivity.class);
        intent.putExtra("MenuID", (int) windowInfo.get("MenuID"));
        startActivity(intent);
        this.finish();

    }

    public void addActual(View view) {
    }

    private void fillSectionListView() {
        List<String> arrayList = new ArrayList<>();
        for(Section section : availableSections) arrayList.add(section.getName());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, arrayList);
        sectionList.setAdapter(arrayAdapter);
    }
}
