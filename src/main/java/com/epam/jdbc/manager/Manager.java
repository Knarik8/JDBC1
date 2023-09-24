package com.epam.jdbc.manager;

import java.util.List;

public interface Manager <Entity, Id>{
    void create (Entity entity);

    Entity getById(Id id);

    List<Entity> getAll();

    void update(Entity entity);

    boolean delete(Id id);
}
