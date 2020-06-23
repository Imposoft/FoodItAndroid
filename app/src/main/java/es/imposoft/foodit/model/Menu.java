package es.imposoft.foodit.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String menuText;
    List<Section> sections;
    String name, description;
    int id;

    public Menu(int id, String name, String description) {
        sections = new ArrayList<>();
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

    public void addSectionToMenu(Section section) { sections.add(section); }

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
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", sections=" + sections.toString() +
                '}';
    }
}
