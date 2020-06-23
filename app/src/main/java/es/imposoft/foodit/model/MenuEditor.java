package es.imposoft.foodit.model;

import java.util.ArrayList;
import java.util.List;

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


