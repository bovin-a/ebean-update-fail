package org.example;

import io.ebean.DB;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Main.okUpdate();
        Main.failUpdate();
    }

    public static void okUpdate() {

        UUID id = UUID.randomUUID();
        Map<String, Object> data = new HashMap<>();
        data.put("numberField", 1);
        data.put("textField", "okUpdate");
        SomeModel someModel = new SomeModel(id, data);

        DB.insert(someModel);

        data.put("booleanField", true);
        DB.update(someModel);
    }

    public static void failUpdate() {

        UUID id = UUID.randomUUID();
        Map<String, Object> data = new HashMap<>();
        data.put("numberField", 2);
        data.put("textField", "failUpdate");
        SomeModel someModel = new SomeModel(id, data);

        DB.insert(someModel);

        data.put("booleanField", false);
        DB.update(SomeModel.class)
            .set("data", data)
            .where()
            .idEq(id)
            .update();
    }
}
