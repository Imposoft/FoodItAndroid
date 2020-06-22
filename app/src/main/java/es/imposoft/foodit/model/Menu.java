package es.imposoft.foodit.model;

import java.util.List;

public class Menu {

    List<Section> sections;
    String name, description;
    int id;

    private String menuText;

    public Menu(List<Section> sections, String name, String description, int id) {
        this.sections = sections;
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu(String menuText) {
        this.menuText = menuText;
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "sections=" + sections +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
