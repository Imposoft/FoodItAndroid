package es.imposoft.foodit.singletons;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.entities.Menu;

public class MenuEditor {

    private static MenuEditor instance;
    List<Menu> savedMenus;

    private MenuEditor() {
        savedMenus = new ArrayList<>();
    }

    public static MenuEditor getInstance() {
        if (instance == null) {
            instance = new MenuEditor();
        }
        return instance;
    }

    public void saveMenu(Menu menu) {
        savedMenus.add(menu);
    }

    public List<Menu> getSavedMenus() {
        return savedMenus;
    }

}


