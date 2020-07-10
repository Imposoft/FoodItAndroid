package es.imposoft.foodit.singletons;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.foodit.entities.Bar;

public class BarEditor {

    private static BarEditor instance;
    List<Bar> savedBars;

    private BarEditor() {
        savedBars = new ArrayList<>();
    }

    public static BarEditor getInstance() {
        if (instance == null) {
            instance = new BarEditor();
        }
        return instance;
    }

    public void saveBar(Bar bar) {
        savedBars.add(bar);
    }

    public List<Bar> getSavedBars() {
        return savedBars;
    }
}
