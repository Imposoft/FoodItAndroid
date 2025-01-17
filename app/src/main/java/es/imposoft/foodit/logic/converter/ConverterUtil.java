package es.imposoft.foodit.logic.converter;

import es.imposoft.foodit.entities.Bar;
import es.imposoft.foodit.entities.Dish;
import es.imposoft.foodit.entities.Menu;
import es.imposoft.foodit.entities.Section;
import es.imposoft.foodit.entities.User;
import es.imposoft.foodit.model.BarDTO;
import es.imposoft.foodit.model.DishDTO;
import es.imposoft.foodit.model.MenuDTO;
import es.imposoft.foodit.model.SectionDTO;
import es.imposoft.foodit.model.UserDTO;

public class ConverterUtil {

    public static Menu convertMenu(MenuDTO menuToConvert){
        Menu convertedMenu = new Menu();
        convertedMenu.setId(menuToConvert.getId());
        convertedMenu.setName(menuToConvert.getName());
        convertedMenu.setDescription(menuToConvert.getDescription());
        convertedMenu.setEdited(false);
        for (SectionDTO sectionToConvert: menuToConvert.getSections()) {
            Section convertedSection = new Section();
            convertedSection.setName(sectionToConvert.getName());
            convertedSection.setDescription(sectionToConvert.getDescription());
            convertedSection.setId(sectionToConvert.getId());
            convertedSection.setEdited(false);
            for (DishDTO dishToConvert:sectionToConvert.getDishes()) {
                Dish convertedDish = new Dish();
                convertedDish.setName(dishToConvert.getName());
                convertedDish.setDescription(dishToConvert.getDescription());
                convertedDish.setPrice(dishToConvert.getPrice());
                convertedDish.setAllergens(dishToConvert.getAllergens());
                convertedDish.setId(dishToConvert.getId());
                convertedSection.addDishToSection(convertedDish);
                convertedDish.setEdited(false);
            }
            convertedMenu.addSectionToMenu(convertedSection);
        }
        return convertedMenu;
    }

    public static MenuDTO convertMenuDTO(Menu menuToConvert) {
        MenuDTO convertedMenu = new MenuDTO();
        convertedMenu.setName(menuToConvert.getName());
        convertedMenu.setDescription(menuToConvert.getDescription());
        convertedMenu.setId(menuToConvert.getId());
        for (Section sectionToConvert: menuToConvert.getSections()) {
            SectionDTO convertedSection = new SectionDTO();
            convertedSection.setId(sectionToConvert.getId());
            convertedSection.setName(sectionToConvert.getName());
            convertedSection.setDescription(sectionToConvert.getDescription());
            for (Dish dishToConvert:sectionToConvert.getDishes()) {
                DishDTO convertedDish = new DishDTO();
                convertedDish.setName(dishToConvert.getName());
                convertedDish.setDescription(dishToConvert.getDescription());
                convertedDish.setPrice(dishToConvert.getPrice());
                convertedDish.setAllergens(dishToConvert.getAllergens());
                convertedDish.setId(dishToConvert.getId());
                convertedSection.addDishToSection(convertedDish);
            }
            convertedMenu.addSectionToMenu(convertedSection);
        }
        return convertedMenu;
    }

    public static User convertUser(UserDTO userToConvert){
        User convertedUser = new User();
        convertedUser.setUsername(userToConvert.getUsername());
        convertedUser.setPassword(userToConvert.getPassword());
        convertedUser.setId(userToConvert.getId());
        return convertedUser;
    }

    public static UserDTO convertUserDTO(User userToConvert){
        UserDTO convertedUser = new UserDTO();
        convertedUser.setUsername(userToConvert.getUsername());
        convertedUser.setPassword(userToConvert.getPassword());
        convertedUser.setId(userToConvert.getId());
        return convertedUser;
    }

    public static Bar convertBar(BarDTO barToConvert) {
        Bar convertedBar = new Bar();
        convertedBar.setId(barToConvert.getId());
        convertedBar.setName(barToConvert.getName());
        convertedBar.setDescription(barToConvert.getDescription());
        convertedBar.setEdited(false);
        return convertedBar;

    }

    public static BarDTO convertBarDTO(Bar barToConvert) {
        BarDTO convertedBar = new BarDTO();
        convertedBar.setId(barToConvert.getId());
        convertedBar.setName(barToConvert.getName());
        convertedBar.setDescription(barToConvert.getDescription());
        return convertedBar;
    }

}
