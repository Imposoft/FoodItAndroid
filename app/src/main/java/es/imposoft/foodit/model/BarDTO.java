package es.imposoft.foodit.model;

import java.util.List;

public class BarDTO {

    int id;
    String name, description;
    List<MenuDTO> menus;

    public BarDTO(int id, String name, String description, List<MenuDTO> menus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.menus = menus;
    }

    public BarDTO() {
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

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
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
