package es.imposoft.foodit.model;

import java.util.List;

public class Bar {

    int id;
    String name, description;
    List<Menu> menus;

    public Bar(int id, String name, String description, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.menus = menus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
