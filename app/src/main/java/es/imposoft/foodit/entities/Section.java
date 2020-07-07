package es.imposoft.foodit.entities;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.model.DishDTO;

public class Section {

    int id;
    String name, description;
    List<Dish> dishes;
    boolean edited;

    public Section() {
        this.dishes = new ArrayList<>();
    }

    public Section(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishes = new ArrayList<>();
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDishToSection(Dish dish) { this.dishes.add(dish); }

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
