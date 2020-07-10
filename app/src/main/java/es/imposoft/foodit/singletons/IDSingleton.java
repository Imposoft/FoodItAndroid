package es.imposoft.foodit.singletons;


public class IDSingleton {

    private static IDSingleton instance;
    private int idbar, idmenu, idsection, iddish, iduser;

    private IDSingleton() {
        iduser = -1;
        idbar = -1;
        idmenu = -1;
        idsection = -1;
        iddish = -1;
    }

    public static IDSingleton getInstance() {
        if (instance == null) {
            instance = new IDSingleton();
        }
        return instance;
    }

    public int getIDBar() { return idbar--; }

    public int getIDMenu() {
        return idmenu--;
    }

    public int getIDSection() {
        return idsection--;
    }

    public int getIDDish() {
        return iddish--;
    }

    public int getIDUser() { return iduser--; }
}
