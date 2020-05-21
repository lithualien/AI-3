package com.github.lithualien.thirdproject.dao;

import java.util.List;

public interface Dao<T> {

    List<T> all();
    void save(T o);
}
