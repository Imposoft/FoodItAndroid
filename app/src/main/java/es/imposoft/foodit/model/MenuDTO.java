package es.imposoft.foodit.model;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {

    private String menuText;
    List<SectionDTO> sections;
    String name, description;
    int id;

    public MenuDTO() {
        this.sections = new ArrayList<>();
    }

    public MenuDTO(int id, String name, String description) {
        sections = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }

    public void addSectionToMenu(SectionDTO section) { sections.add(section); }

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

    public MenuDTO(String menuText) {
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
        return name;
    }
}
