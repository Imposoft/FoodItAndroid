package es.imposoft.foodit.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Section {

    int id;
    String name, description;
    List<Dish> dishes;
    static List<Section> sections;


    public Section(int id, String name, String description, @Nullable List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishes = dishes;
    }

    public static Section getSectionFromName(String name) {
        for(Section section : sections) {
            if (section.getName().equals(name)) return section;
        }
        return null;
    }

    public static ArrayList<Section> getAvailableSections() {
        return null;
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Section> getSectionsFromMenu() { return sections; }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
