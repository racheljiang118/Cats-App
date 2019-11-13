package com.example.cats;

import java.util.HashMap;
import java.util.List;

public class FakeDatabase {

    public static HashMap<String, Cats> cats = new HashMap<>();

    public static Cats getCatsById(String catsID) {
        return cats.get(catsID);
    }

    public static List<Cats> getAllCats() {
        return (List) cats.values();
    }

    public static void saveCatsToFakeDatabase(List<Cats> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cats cat = catsToSave.get(i);
            cats.put(cat.getId(), cat);
        }
    }
}
