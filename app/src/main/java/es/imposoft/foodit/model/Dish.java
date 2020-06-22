package es.imposoft.foodit.model;

import android.media.Image;

import java.util.List;

public class Dish {

    String name, description;
    List<Allergen> allergens;
    float price;
    Image image;

    public Dish(String name, String description, List<Allergen> allergens, float price, Image image) {
        this.name = name;
        this.description = description;
        this.allergens = allergens;
        this.price = price;
        this.image = image;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
