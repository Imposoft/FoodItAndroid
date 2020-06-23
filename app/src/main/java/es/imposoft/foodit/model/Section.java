package es.imposoft.foodit.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Section {

    int id;
    String name, description;
    List<Dish> dishes = new ArrayList<>();
    static List<Section> sections;


    public Section(int id, String name, String description, @Nullable List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishes = dishes;
    }

    public Section(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishes = new ArrayList<>();
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

    public void addDishToSection(Dish dish) { this.dishes.add(dish); }

    public List<Section> getSectionsFromMenu() { return null; }

    @Override
    public String toString() {
        return name;
    }
}
