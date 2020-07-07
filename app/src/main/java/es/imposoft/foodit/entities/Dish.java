package es.imposoft.foodit.entities;

import android.media.Image;

import java.util.List;

import es.imposoft.foodit.model.Allergen;

public class Dish {

    int id;
    String name, description;
    List<Allergen> allergens;
    double price;
    Image image;
    boolean edited;

    public Dish() { }

    public Dish(int id, String name, String description, List<Allergen> allergens, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.allergens = allergens;
        this.price = price;
        edited =  true;
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

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
