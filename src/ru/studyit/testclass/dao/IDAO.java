package ru.studyit.testclass.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDAO<T> {
    T get(UUID id);

    List<T> getAll();

    void save(T t);

    void update(T t);//, String[] params);

    void delete(T t);
}
