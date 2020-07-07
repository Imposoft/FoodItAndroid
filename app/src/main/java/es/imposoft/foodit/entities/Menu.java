package es.imposoft.foodit.entities;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.model.SectionDTO;

public class Menu {

    private String menuText;
    List<Section> sections;
    String name, description;
    int id;
    boolean edited;

    public Menu() {
        this.sections = new ArrayList<>();
    }

    public Menu(int id, String name, String description) {
        this.sections = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.id = id;
        edited = true;
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

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }


    @Override
    public String toString() {
        return name;
    }
}
