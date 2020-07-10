package es.imposoft.foodit.entities;

import java.util.List;

import es.imposoft.foodit.model.MenuDTO;

public class Bar {

    boolean edited;
    int id;
    String name, description;
    List<Menu> menus;

    public Bar() { }

    public Bar(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.menus = menus;
        edited = true;
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

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", menus=" + menus +
                '}';
    }
}
