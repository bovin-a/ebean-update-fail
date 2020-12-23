package org.example;

import io.ebean.annotation.DbJsonB;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;
import java.util.UUID;

@Entity
public final class SomeModel {

    @Id
    private final UUID id;

    @DbJsonB
    private final Map<String, Object> data;

    public SomeModel(UUID id, Map<String, Object> map) {

        this.data = map;
        this.id = id;
    }

    public UUID getId() {

        return id;
    }

    public Map<String, Object> getData() {

        return data;
    }
}
