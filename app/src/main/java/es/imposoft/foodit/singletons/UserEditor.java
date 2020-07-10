package es.imposoft.foodit.singletons;

import es.imposoft.foodit.entities.User;

public class UserEditor {

    private static UserEditor instance;
    User activeUser;

    private UserEditor() { }

    public static UserEditor getInstance() {
        if (instance == null) {
            instance = new UserEditor();
        }
        return instance;
    }

    public void saveUser(User user) {
        activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
    }
}
